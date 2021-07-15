INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 1, 'admin'),
(2, 1, 'user'),
(3, 1, 'ROLE_ADMIN');

INSERT INTO acl_class (id, class) VALUES
(1, 'ru.otus.spring.mvc.dto.BookDto');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, 1, NULL, 3, 0),
(2, 1, 2, NULL, 3, 0);

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask,
                       granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 1, 1, 1, 1),
(2, 1, 2, 3, 2, 1, 1, 1),
(3, 2, 1, 1, 1, 1, 1, 1),
(4, 2, 2, 2, 1, 1, 1, 1),
(5, 2, 3, 3, 2, 1, 1, 1);
