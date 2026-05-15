CREATE TABLE booking_orders
(
    id       BIGSERIAL PRIMARY KEY,
    user_id BIGINT not null,
    black_hole_id BIGINT not null,


    orbit_radius_km DOUBLE PRECISION NOT NULL,
    expected_ship_years DOUBLE PRECISION NOT NULL,
    expected_earth_years DOUBLE PRECISION NOT NULL,

    status VARCHAR(50) not null default 'DRAFT',

    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_booking_orders_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);