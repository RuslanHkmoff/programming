"use strict"

class Operation {
    constructor(symbol, operation, ...args) {
        this.args = args;
        this.symbol = symbol;
        this.operation = operation;
    }


    evaluate(x, y, z) {
        return this.operation(...this.args.map(arg => arg.evaluate(x, y, z)))
    }

    toString() {
        return this.args.join(" ") + " " + this.symbol;
    }

    prefix() {
        if (this.args.length === 0) {
            return '(' + this.symbol + ' )';
        }
        return '(' + this.args.reduce((str, cur) => str + " " + cur.prefix(), this.symbol) + ')';
    }
}

class Avg extends Operation {
    constructor(...args) {
        super("avg", (...args) => args.reduce((sum, x) => sum + x, 0) / args.length, ...args);
    }
}

class Sum extends Operation {
    constructor(...args) {
        super("sum", (...args) => args.reduce((sum, x) => sum + x, 0), ...args);
    }
}


class Const {
    constructor(value) {
        this.value = parseFloat(value);
    }

    evaluate(x, y, z) {
        return this.value;
    }

    toString() {
        return "" + this.value;
    }

    prefix() {
        return "" + this.value;
    }
}

class Variable {
    constructor(name) {
        this.name = name;
    }

    evaluate(x, y, z) {
        switch (this.name) {
            case `x`:
                return x;
            case 'y':
                return y;
            case 'z':
                return z;
        }
    }

    toString() {
        return "" + this.name;
    }

    prefix() {
        return this.name.toString();
    }
}

class Negate extends Operation {
    constructor(expr) {
        super("negate", (a) => -a, expr);
    }
}

class Exp extends Operation {
    constructor(expr) {
        super("exp", Math.exp, expr);
    }
}

class Ln extends Operation {
    constructor(expr) {
        super("ln", Math.log, expr);
    }
}


class Add extends Operation {
    constructor(...args) {
        super("+", (a, b) => a + b, ...args);
    }
}

class Subtract extends Operation {
    constructor(...args) {
        super("-", (a, b) => a - b, ...args);
    }
}

class Multiply extends Operation {
    constructor(...args) {
        super("*", (a, b) => a * b, ...args);
    }
}

class Divide extends Operation {
    constructor(...args) {
        super("/", (a, b) => a / b, ...args);
    }
}

const Operators = {
    "+": [2, (...args) => new Add(...args)],
    "*": [2, (...args) => new Multiply(...args)],
    "-": [2, (...args) => new Subtract(...args)],
    "/": [2, (...args) => new Divide(...args)],
    "ln": [1, a => new Ln(a)],
    "negate": [1, a => new Negate(a)],
    "exp": [1, a => new Exp(a)],
    "avg": [0, (...args) => new Avg(...args)],
    "sum": [0, (...args) => new Sum(...args)]

};
const parse = (expr) => {
    const stack = [];
    const tokens = expr.trim().split(/\s+/)
    for (let token of tokens) {
        if (token in Operators) {
            let operands = stack.splice(-Operators[token][0], Operators[token][0]);
            stack.push(Operators[token][1](...operands));
        } else if (/^[xyz]$/.test(token)) {
            stack.push(new Variable(token));
        } else {
            stack.push(new Const(token));
        }
    }
    return stack.pop();
}

function ParsingError(message) {
    Error.call(this, message);
    this.message = message;
}

ParsingError.prototype = Object.create(Error.prototype)

function parsePrefix(input) {
    checkBalance(input);
    let pos = 0;
    input = input.trim();
    if (!input) {
        throw new ParsingError("empty expression")
    }
    const tokens = input.replace(/\(/g, " ( ").replace(/\)/g, " ) ").trim().split(/\s+/);

    function parseToken() {
        pos++;
        const token = tokens.shift();
        if (token === undefined) {
            throw new ParsingError("Unexpected end of input");
        }
        if (token === "(") {
            if (!(tokens[0] in Operators)) {
                throw new ParsingError("Expected expression, actual: " + tokens[0]);
            }
            const expr = parseToken();
            tokens.shift();
            return expr;
        } else if (/^[xyz]$/.test(token)) {
            return new Variable(token);
        } else if (token in Operators) {
            let operands = [];
            let countOfArg = 0;
            let need = Operators[token][0];
            while (tokens[0] !== ")") {
                countOfArg++;
                if (tokens[0] in Operators) {
                    throw new ParsingError("Unexpected operator '" + token[0]
                        + "' at pos: " + pos);
                }
                operands.push(parseToken());
            }
            if (need !== 0 && countOfArg !== need) {
                throw new ParsingError("Illegal arguments in op, expected: " + need + " actual: " + countOfArg);
            }
            return Operators[token][1](...operands);
        } else if (!isNaN(Number(token)) && !isNaN(parseFloat(token))) {
            return new Const(token);
        } else {
            throw new ParsingError("Unexpected token '" + token + "' at pos " + pos
                + " in expr: " + input);
        }
    }

    function checkBalance(s) {
        let balance = 0;
        for (let i = 0; i < s.length; i++) {
            if (balance < 0) throw new ParsingError("Missing '('");
            if (s[i] === '(') balance++;
            else if (s[i] === ')') {
                balance--;
            }
        }
        if (balance !== 0) {
            throw new ParsingError('illegal counts of brackets');
        }
    }

    const expr = parseToken();
    if (tokens.length > 0) {
        throw new ParsingError("Unexpected token at pos: " + pos + " in expr: " + input);
    }
    return expr;
}

// let ex = parsePrefix("(negate)");
