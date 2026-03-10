import {describe, it, expect, test} from 'vitest';

describe ('Setup', () => {
    it('The test is working', () => {
        expect(7 * 7).toBe(49);
    });
    test('Should concatenate a number and a string', () => {
        expect(3 + 'a').toBe('3a');
    })
});