ALTER TABLE booking_orders
    ADD COLUMN actual_ship_years DOUBLE PRECISION,
    ADD COLUMN actual_earth_years DOUBLE PRECISION,
    ADD COLUMN discrepancy_percentage DOUBLE PRECISION,
    ADD COLUMN compensation_tier INT;