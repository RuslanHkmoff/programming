"use strict"
const abstractCalc = operation => (...args) =>
    (...values) => operation(...args.map(arg => arg(...values)));
const add = abstractCalc((left, right) => (left + right))
const subtract = abstractCalc((left, right) => (left - right))
const multiply = abstractCalc((left, right) => (left * right))
const negate = abstractCalc(value => -value)
const divide = abstractCalc((left, right) => (left / right))
const sin = abstractCalc(Math.sin)
const cos = abstractCalc(Math.cos)
const floor = abstractCalc(Math.floor)
const ceil = abstractCalc(Math.ceil)
const madd = abstractCalc((left, middle, right) => left * middle + right)

const variable = name => {
    const index = {x: 0, y: 1, z: 2}[name];
    return (x, y, z) => [x, y, z][index];
};
const cnst = value => _ => parseFloat(value);
const one = cnst(1)
const two = cnst(2)
const constants = {
    "one": one,
    "two": two
}
const parse = (expr) => {
    const stack = [];
    const tokens = expr.trim().split(/\s+/)
    let first, second;
    for (let token of tokens) {
        switch (token) {
            case '+':
                stack.push(add(stack.pop(), stack.pop()));
                break;
            case '-':
                first = stack.pop()
                second = stack.pop();
                stack.push(subtract(second, first));
                break;
            case '*':
                stack.push(multiply(stack.pop(), stack.pop()));
                break;
            case '/':
                first = stack.pop()
                second = stack.pop();
                stack.push(divide(second, first));
                break;
            case 'negate':
                stack.push(negate(stack.pop()));
                break;
            case 'sin':
                stack.push(sin(stack.pop()))
                break
            case 'cos':
                stack.push(cos(stack.pop()))
                break
            default:
                if (/^[xyz]$/.test(token)) {
                    stack.push(variable(token));
                } else {
                    if (token in constants) {
                        stack.push(constants[token])
                    } else {
                        stack.push(cnst(token));
                    }
                }
        }
    }
    return stack.pop();
}

let example = parse("x x 2 - * x * 1 +")
for (let i = 0; i < 10; ++i) {
    console.log(example(i, 0, 0))
}