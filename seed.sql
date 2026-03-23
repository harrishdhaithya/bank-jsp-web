-- Sample seed data
-- Passwords:
--   Alice / Bob / Charlie -> password123  (sha256: ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f)
--   Admin1 / Admin2       -> admin123     (sha256: 240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9)

-- Users
INSERT INTO public."User" (accno, fname, lname, phone, email, balance, passwordhash) VALUES
('1000000001', 'Alice',   'Smith',   '9876543210', 'alice@example.com',   50000.00, 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f'),
('1000000002', 'Bob',     'Johnson', '9876543211', 'bob@example.com',     30000.00, 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f'),
('1000000003', 'Charlie', 'Brown',   '9876543212', 'charlie@example.com', 10000.00, 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f');

-- Admins
INSERT INTO public."Admin" (empid, name, phone, email, passwordhash) VALUES
('2000000001', 'Admin One', '9000000001', 'admin1@bank.com', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9'),
('2000000002', 'Admin Two', '9000000002', 'admin2@bank.com', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9');

-- TOTP secrets (base32 encoded, use these in Google Authenticator)
INSERT INTO public."UserSecret" (accno, secret) VALUES
('1000000001', 'JBSWY3DPEHPK3PXP'),
('1000000002', 'JBSWY3DPEHPK3PXQ'),
('1000000003', 'JBSWY3DPEHPK3PXR');

INSERT INTO public."AdminSecret" (empid, secret) VALUES
('2000000001', 'JBSWY3DPEHPK3PXS'),
('2000000002', 'JBSWY3DPEHPK3PXT');

-- Sample transactions
INSERT INTO public."Transaction" (src, dest, amount, date, time) VALUES
('1000000001', '1000000002', 5000.00,  '2026-03-20', '10:30:00'),
('1000000002', '1000000003', 2000.00,  '2026-03-21', '14:15:00'),
('1000000001', '1000000003', 1500.00,  '2026-03-22', '09:00:00');
