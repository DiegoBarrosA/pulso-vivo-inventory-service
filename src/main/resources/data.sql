-- Sample data for testing the inventory service
INSERT INTO product (name, description, quantity, category, active) VALUES 
('Laptop Dell XPS 13', 'High-performance ultrabook with 13-inch display', 25, 'Electronics', 1);

INSERT INTO product (name, description, quantity, category, active) VALUES 
('iPhone 15', 'Latest Apple smartphone with advanced features', 50, 'Electronics', 1);

INSERT INTO product (name, description, quantity, category, active) VALUES 
('Samsung Galaxy S24', 'Premium Android smartphone', 30, 'Electronics', 1);

INSERT INTO product (name, description, quantity, category, active) VALUES 
('Office Chair', 'Ergonomic office chair with lumbar support', 15, 'Furniture', 1);

INSERT INTO product (name, description, quantity, category, active) VALUES 
('Standing Desk', 'Height-adjustable standing desk', 8, 'Furniture', 1);

INSERT INTO product (name, description, quantity, category, active) VALUES 
('Wireless Mouse', 'Bluetooth wireless mouse', 100, 'Accessories', 1);

INSERT INTO product (name, description, quantity, category, active) VALUES 
('Mechanical Keyboard', 'RGB backlit mechanical keyboard', 45, 'Accessories', 1);

INSERT INTO product (name, description, quantity, category, active) VALUES 
('Monitor 27 inch', '4K UHD monitor 27 inch', 20, 'Electronics', 1);

INSERT INTO product (name, description, quantity, category, active) VALUES 
('Headphones', 'Noise-cancelling wireless headphones', 35, 'Accessories', 1);

INSERT INTO product (name, description, quantity, category, active) VALUES 
('Tablet Pro', 'Professional tablet for creative work', 12, 'Electronics', 1);

-- Low stock items for testing low-stock endpoint
INSERT INTO product (name, description, quantity, category, active) VALUES 
('Printer Ink', 'Black ink cartridge', 5, 'Office Supplies', 1);

INSERT INTO product (name, description, quantity, category, active) VALUES 
('USB Cable', 'USB-C charging cable', 3, 'Accessories', 1);