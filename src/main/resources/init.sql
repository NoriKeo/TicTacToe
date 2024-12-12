create table if not exists public.accounts
(
    player_id         serial
        primary key,
    player_name       varchar(255),
    passwort          varchar(255),
    security_question varchar(255)
);

alter table public.accounts
    owner to postgres;

create table if not exists public.score
(
    score_id       serial
        primary key,
    player_id      integer not null
        references public.accounts,
    computer_score integer,
    player_score   integer,
    draw_score     integer
);

alter table public.score
    owner to postgres;

create table if not exists public.unfinished_match
(
    unfinished_id            serial
        primary key,
    player_id                integer not null
        references public.accounts,
    computer_playsunfinished integer,
    player_playsunfinished   integer
);

alter table public.unfinished_match
    owner to postgres;

create table if not exists public.match_time
(
    time_id         serial
        primary key,
    player_id       integer not null
        references public.accounts,
    match_data      date,
    match_data_time timestamp
);

alter table public.match_time
    owner to postgres;

create table if not exists public.match_history
(
    match_id       serial
        primary key,
    player_id      integer not null
        references public.accounts,
    computer_plays integer,
    player_plays   integer,
    win            boolean
);

alter table public.match_history
    owner to postgres;
