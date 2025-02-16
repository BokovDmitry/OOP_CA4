DROP DATABASE IF EXISTS finance_manager;
CREATE DATABASE IF NOT EXISTS finance_manager;
USE finance_manager;

DROP TABLE IF EXISTS expenses, incomes;

CREATE TABLE expenses (
    expenseID INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    amount DOUBLE NOT NULL,
    dateIncurred DATE NOT NULL
);

INSERT INTO expenses (title, category, amount, dateIncurred) VALUES
('Groceries', 'Food', 45.75, '2025-02-10'),
('Uber Ride', 'Transport', 12.50, '2025-02-12'),
('Concert Ticket', 'Entertainment', 85.00, '2025-01-30'),
('Coffee with Friends', 'Food', 9.99, '2025-02-14'),
('Gym Membership', 'Health', 40.00, '2025-02-01'),
('Electricity Bill', 'Utilities', 120.30, '2025-02-05'),
('Movie Night', 'Entertainment', 15.50, '2025-02-08'),
('New Headphones', 'Electronics', 75.90, '2025-01-28'),
('Bus Pass', 'Transport', 50.00, '2025-02-03'),
('Lunch at Restaurant', 'Food', 25.40, '2025-02-11');

CREATE TABLE incomes (
    incomeID INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) DEFAULT NULL,
    amount DOUBLE NOT NULL,
    dateEarned DATE NOT NULL
);

INSERT INTO incomes (title, amount, dateEarned) VALUES
('Salary', 3500, '2025-02-10');
