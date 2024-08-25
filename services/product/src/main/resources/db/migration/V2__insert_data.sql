INSERT INTO category (id, name, description) VALUES
(1, 'Electronics', 'Devices and gadgets'),
(2, 'Books', 'Various kinds of books'),
(3, 'Clothing', 'What Men and Women wear'),
(4, 'Home & Kitchen', 'Home improvement and kitchenware'),
(5, 'Sports', 'Sports equipment and gear');


INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES
(nextval('product_seq'), 'Bluetooth Headphones', 'Noise-cancelling over-ear headphones', 75, 199.99, 1),
(nextval('product_seq'), '4K TV', '55-inch Ultra HD smart TV', 25, 799.99, 1),
(nextval('product_seq'), 'Cookbook', 'Healthy recipes for everyday cooking', 50, 29.99, 2),
(nextval('product_seq'), 'Jeans', 'Stylish denim jeans', 100, 49.99, 3),
(nextval('product_seq'), 'Blender', 'High-speed kitchen blender', 40, 59.99, 4),
(nextval('product_seq'), 'Basketball', 'Official size and weight basketball', 20, 29.99, 5),
(nextval('product_seq'), 'Smartwatch', 'Fitness tracker smartwatch with heart rate monitor', 60, 249.99, 1),
(nextval('product_seq'), 'E-book Reader', 'Lightweight e-reader with adjustable light', 45, 129.99, 2),
(nextval('product_seq'), 'Winter Coat', 'Warm and waterproof winter coat', 30, 89.99, 3),
(nextval('product_seq'), 'Pressure Cooker', 'Multi-function pressure cooker', 35, 79.99, 4),
(nextval('product_seq'), 'Tennis Racket', 'Professional-grade tennis racket', 10, 99.99, 5);