package ru.otus.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.mvc.domain.Book;
import ru.otus.spring.mvc.dto.BookDto;
import ru.otus.spring.mvc.exception.BookNotFoundException;
import ru.otus.spring.mvc.exception.InputNotCorrectException;
import ru.otus.spring.mvc.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

import static ru.otus.spring.mvc.dto.convertor.BookConvertor.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    protected MutableAclService mutableAclService;

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostFilter("hasPermission(filterObject, read)")
    public List<BookDto> getAllBooks() {
        return toListBookDto(bookRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public BookDto getBookById(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new BookNotFoundException();
        }
        return toBookDto(bookOptional.get());
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new BookNotFoundException();
        }
        bookRepository.delete(bookOptional.get());
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public BookDto saveBook(BookDto book) {
        if (book == null || book.getBookName().trim().isEmpty() || book.getGenre() == null) {
            throw new InputNotCorrectException();
        }

        //todo нужно переделать в бд генерацию id со строк на числовую последовательность
        BookDto dto = toBookDto(bookRepository.save(toBook(book)));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Sid owner = new PrincipalSid( authentication );
        ObjectIdentity oid = new ObjectIdentityImpl( dto.getClass(), dto.getId() );

        final Sid admin = new GrantedAuthoritySid("ROLE_ADMIN");

        MutableAcl acl = mutableAclService.createAcl( oid );
        acl.setOwner( owner );
        acl.insertAce( acl.getEntries().size(), BasePermission.ADMINISTRATION, admin, true );

        mutableAclService.updateAcl( acl );

        return dto;
    }
}
