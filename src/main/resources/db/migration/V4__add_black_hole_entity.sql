CREATE TABLE black_holes (
                             id BIGSERIAL PRIMARY KEY,
                             name VARCHAR(255) NOT NULL UNIQUE,
                             constellation VARCHAR(100),
                             distance_ly DOUBLE PRECISION NOT NULL,
                             mass_solar DOUBLE PRECISION NOT NULL,
                             description TEXT,
                             time_dilation_factor DOUBLE PRECISION NOT NULL
);


INSERT INTO black_holes (name, constellation, distance_ly, mass_solar, description, time_dilation_factor)
VALUES
    (
        'Gaia BH1',
        'Ophiuchus',
        1560.0,
        9.6,
        'The closest known black hole to Earth! A dormant stellar-mass black hole perfect for a quick weekend getaway. Very low risk of spaghettification if you stay on the designated tourist orbits.',
        20.0
    ),
    (
        'Cygnus X-1',
        'Cygnus',
        6070.0,
        21.2,
        'The first universally accepted black hole. Features a stunning, blindingly bright accretion disk siphoning gas from its blue supergiant companion star. Bring premium polarized sunglasses.',
        50.0
    ),
    (
        'V404 Cygni',
        'Cygnus',
        7800.0,
        9.0,
        'A microquasar famous for its unpredictable and spectacular outbursts of X-ray radiation. Recommended only for extreme thrill-seekers. Radiation shielding suits are mandatory.',
        45.0
    ),
    (
        'GRO J1655-40',
        'Scorpius',
        11000.0,
        5.3,
        'One of the fastest spinning black holes ever discovered, rotating at 450 times per second! Experience the ultimate frame-dragging effect as space-time itself is whipped into a frenzy.',
        80.0
    ),
    (
        'Sagittarius A*',
        'Sagittarius',
        26670.0,
        4310000.0,
        'The supermassive heart of our Milky Way galaxy. Despite its massive size, its tidal forces near the event horizon are remarkably gentle. A classic, must-see destination for all deep-space tourists.',
        10.0
    ),
    (
        'Centaurus A (NGC 5128)',
        'Centaurus',
        13000000.0,
        55000000.0,
        'Located in the fifth brightest galaxy in the sky. It features colossal relativistic jets blasting out radio-emitting plasma at a fraction of the speed of light. Excellent for cosmic photography.',
        15.0
    ),
    (
        'M87* (Messier 87)',
        'Virgo',
        53400000.0,
        6500000000.0,
        'The celebrity black hole! The first black hole ever directly imaged by humanity. Features a legendary 5,000-light-year-long jet. A premium, once-in-a-lifetime intergalactic cruise.',
        100.0
    ),
    (
        'OJ 287',
        'Cancer',
        3500000000.0,
        18350000000.0,
        'A spectacular binary black hole system! Watch a "smaller" 150-million-mass black hole crash through the accretion disk of its 18-billion-mass titan partner twice every 12 years. Timing your ticket is crucial.',
        250.0
    ),
    (
        'Holmberg 15A',
        'Cetus',
        700000000.0,
        40000000000.0,
        'One of the most massive local black holes discovered. Its event horizon is so unfathomably large it could swallow our entire Solar System dozens of times over. Not for the faint of heart.',
        500.0
    ),
    (
        'TON 618',
        'Canes Venatici',
        18200000000.0,
        66000000000.0,
        'The absolute behemoth. An ultramassive black hole powering one of the most luminous quasars in the universe. The ultimate frontier of your cosmic journey. Time dilation here will erase eras of Earth history.',
        1000.0
    );