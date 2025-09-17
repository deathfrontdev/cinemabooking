CREATE TABLE movies (
                        id BIGSERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        genre VARCHAR(255) NOT NULL,
                        release_year INT NOT NULL,
                        description TEXT,
                        poster_image_url VARCHAR(500)
);

CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       user_name VARCHAR(100) NOT NULL UNIQUE,
                       email VARCHAR(150) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
);

CREATE TABLE theaters (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          location VARCHAR(255) NOT NULL,
                          capacity INT NOT NULL
);

CREATE TABLE showtimes (
                           id BIGSERIAL PRIMARY KEY,
                           movie_id BIGINT NOT NULL,
                           theater_id BIGINT NOT NULL,
                           show_date DATE NOT NULL,
                           show_time TIME NOT NULL,
                           total_seats INT NOT NULL,
                           available_seats INT NOT NULL,
                           price DOUBLE PRECISION NOT NULL,

                           CONSTRAINT fk_showtimes_movie FOREIGN KEY (movie_id) REFERENCES movies (id) ON DELETE CASCADE,
                           CONSTRAINT fk_showtimes_theater FOREIGN KEY (theater_id) REFERENCES theaters (id) ON DELETE CASCADE
);

CREATE TABLE reservations (
                              id BIGSERIAL PRIMARY KEY,
                              user_id BIGINT NOT NULL,
                              showtime_id BIGINT NOT NULL,
                              reservation_time TIMESTAMP NOT NULL DEFAULT NOW(),
                              status VARCHAR(50) NOT NULL DEFAULT 'CONFIRMED',
                              paid BOOLEAN NOT NULL DEFAULT FALSE,
                              total_price NUMERIC(10,2) NOT NULL,

                              CONSTRAINT fk_reservation_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                              CONSTRAINT fk_reservation_showtime FOREIGN KEY (showtime_id) REFERENCES showtimes (id) ON DELETE CASCADE
);

CREATE TABLE seats (
                       id BIGSERIAL PRIMARY KEY,
                       showtime_id BIGINT NOT NULL,
                       seat_number VARCHAR(50) NOT NULL,
                       is_reserved BOOLEAN NOT NULL DEFAULT FALSE,
                       reservation_id BIGINT,

                       CONSTRAINT fk_seat_showtime FOREIGN KEY (showtime_id) REFERENCES showtimes (id) ON DELETE CASCADE,
                       CONSTRAINT fk_seat_reservation FOREIGN KEY (reservation_id) REFERENCES reservations (id) ON DELETE SET NULL
);

CREATE TABLE reviews (
                         id BIGSERIAL PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         movie_id BIGINT NOT NULL,
                         comment TEXT NOT NULL,
                         rating INT NOT NULL,
                         created_at TIMESTAMP DEFAULT NOW(),
                         updated_at TIMESTAMP DEFAULT NOW(),
                         upvotes INT DEFAULT 0,
                         downvotes INT DEFAULT 0,
                         helpful_tags VARCHAR(255),
                         status VARCHAR(50) DEFAULT 'APPROVED',

                         CONSTRAINT fk_review_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                         CONSTRAINT fk_review_movie FOREIGN KEY (movie_id) REFERENCES movies (id) ON DELETE CASCADE,
                         CONSTRAINT chk_rating CHECK (rating >= 1 AND rating <= 10)
);

CREATE TABLE payments (
                          id BIGSERIAL PRIMARY KEY,
                          reservation_id BIGINT UNIQUE NOT NULL,
                          payment_intent_id VARCHAR(255) NOT NULL,
                          amount DOUBLE PRECISION NOT NULL,
                          status VARCHAR(50) NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                          updated_at TIMESTAMP,
                          receipt_url VARCHAR(500),
                          pdf_receipt_path VARCHAR(500),

                          CONSTRAINT fk_payment_reservation FOREIGN KEY (reservation_id) REFERENCES reservations (id) ON DELETE CASCADE
);

-- INSERTS TO SCHEMAS
-- 🎬 Movies
INSERT INTO movies (title, genre, release_year, description, poster_image_url)
VALUES
    ('Inception', 'Sci-Fi', 2010, 'A thief who steals corporate secrets through dreams.', 'https://example.com/inception.jpg'),
    ('The Dark Knight', 'Action', 2008, 'Batman faces the Joker in Gotham.', 'https://example.com/darkknight.jpg');

-- 👤 Users
INSERT INTO users (user_name, email, password, role)
VALUES
    ('john_doe', 'john@example.com', 'password123', 'USER'),
    ('admin_user', 'admin@example.com', 'adminpass', 'ADMIN');

-- 🎭 Theaters
INSERT INTO theaters (name, location, capacity)
VALUES
    ('Cinema Park', 'New York', 200),
    ('IMAX Central', 'Los Angeles', 300);

-- ⏰ Showtimes
INSERT INTO showtimes (movie_id, theater_id, show_date, show_time, total_seats, available_seats, price)
VALUES
    (1, 1, '2025-09-01', '19:30:00', 200, 180, 12.50),
    (2, 2, '2025-09-02', '20:00:00', 300, 295, 15.00);

-- 🎟 Reservations
INSERT INTO reservations (user_id, showtime_id, reservation_time, status, paid, total_price)
VALUES
    (1, 1, NOW(), 'CONFIRMED', TRUE, 25.00),
    (2, 2, NOW(), 'CONFIRMED', FALSE, 30.00);

-- 💺 Seats
INSERT INTO seats (showtime_id, seat_number, is_reserved, reservation_id)
VALUES
    (1, 'A1', TRUE, 1),
    (1, 'A2', TRUE, 1),
    (2, 'B1', TRUE, 2),
    (2, 'B2', FALSE, NULL);

-- 📝 Reviews
INSERT INTO reviews (user_id, movie_id, comment, rating, created_at, updated_at, upvotes, downvotes, helpful_tags, status)
VALUES
    (1, 1, 'Amazing movie, mind-blowing!', 9, NOW(), NOW(), 10, 1, 'thrilling,mind-bending', 'APPROVED'),
    (2, 2, 'Best Batman ever!', 10, NOW(), NOW(), 15, 0, 'action-packed,epic', 'APPROVED');

-- 💳 Payments
INSERT INTO payments (reservation_id, payment_intent_id, amount, status, created_at, updated_at, receipt_url, pdf_receipt_path)
VALUES
    (1, 'pi_123456789', 25.00, 'SUCCESS', NOW(), NOW(), 'https://example.com/receipt1', '/receipts/receipt1.pdf'),
    (2, 'pi_987654321', 30.00, 'PENDING', NOW(), NULL, NULL, NULL);





