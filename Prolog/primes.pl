odd(N) :- 1 is mod(N, 2).
even(N) :- 0 is mod(N, 2).

check(_, 1) :- !.
check(N, K) :-
    K > 1,
    odd(N),
    mod(N, K) =\= 0,
    K1 is K - 1,
    check(N, K1).

prime(2) :- !.
prime(N) :-
    N > 2,
    SqrtN is floor(sqrt(N)),
    check(N, SqrtN).

composite(N) :- \+ prime(N).

concat([], B, [B]).
concat([H|T], B, [H|R]) :- concat(T, B, R).

get_divisors(1, 1, []) :- !.
get_divisors(_, 1, _) :- !.
get_divisors(1, _, []) :- !.
get_divisors(N, K, R) :-
    (K > 1, mod(N, K) =:= 0,
        prime(K),
        N1 is div(N, K),
        K1 is K,
        get_divisors(N1, K1, Tmp),
        concat(Tmp, K1, R), !)
    ;
    (K1 is K - 1,
        get_divisors(N, K1, R), !).

get_divisors(N, R) :- K is N, get_divisors(N, K, R).

equals([], []).
equals([H1 | T1], [H2 | T2]) :- H1 == H2, equals(T1, T2).

prime_divisors(N, V) :- get_divisors(N, V), !.
prime_divisors(N, Divisors) :- get_divisors(N, R), equals(R, Divisors).
duplicate([], []) :- !.
duplicate([H | T], [H, H | T1]) :-
    duplicate(T, T1).
square_divisors(N, D) :-  prime_divisors(N, Divisors), duplicate(Divisors, D).