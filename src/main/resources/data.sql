INSERT INTO CUSTOMER(customer_id,customer_name,customer_surname) VALUES
(323132, 'alper', 'kopuz'),
(323133, 'neo', 'matrix'),
(323134, 'trinity', 'matrix');

INSERT INTO ACCOUNT(account_id,balance,customer_id) VALUES
(234123,'100.2',323132),
(234124,'50.6',323132),
(234125,'90.8',323132),
(234126,'34.1',323133),
(234127,'20.1',323134);

INSERT INTO TRANSACTION (transaction_id, transaction_amount,account_id) VALUES
(123121, 100.2, 234123),
(123122, 50.2, 234123),
(123123, 10.4, 234124),
(123124, 10.4, 234125),
(123125, 10.4, 234126),
(123126, 10.4, 234127);


