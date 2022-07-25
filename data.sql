USE bank;

INSERT INTO `role` (`id`, `name`) VALUES (1, 'CUSTOMER');
INSERT INTO `role` (`id`, `name`) VALUES (2, 'STAFF');
INSERT INTO `role` (`id`, `name`) VALUES (3, 'ADMIN');

INSERT INTO `bank`.`transaction` (`id`, `amount`, `card_type`, `date`, `reference`, `account_id`) VALUES ('1', '55.66', 'DB', '2022-07-23 16:57:18.220000', 'BOA1001', '1');