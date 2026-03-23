package triangle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for Triangle.classify() using:
 *  - Boundary Value Analysis (BVA)
 *  - Equivalence Partitioning (EP)
 *  - Error Guessing (EG)
 *  - Branch / Condition Coverage
 */
class TriangleTest {

    // =========================================================
    // BOUNDARY VALUE ANALYSIS — variable 'a'
    // =========================================================

    @Test @DisplayName("BVA: a=1 (min boundary)")
    void bva_a_min() {
        // a=1, b=100, c=100 → valid isosceles
        assertEquals("Isosceles", Triangle.classify(1, 100, 100));
    }

    @Test @DisplayName("BVA: a=2 (min+1)")
    void bva_a_min_plus1() {
        assertEquals("Isosceles", Triangle.classify(2, 100, 100));
    }

    @Test @DisplayName("BVA: a=199 (max-1)")
    void bva_a_max_minus1() {
        assertEquals("Isosceles", Triangle.classify(199, 100, 100));
    }

    @Test @DisplayName("BVA: a=200 (max boundary)")
    void bva_a_max() {
        assertEquals("Isosceles", Triangle.classify(200, 101, 101));
    }

    @Test @DisplayName("BVA: a=0 (below min) → invalid")
    void bva_a_below_min() {
        String result = Triangle.classify(0, 100, 100);
        assertTrue(result.contains("a"), "Should mention 'a'");
    }

    @Test @DisplayName("BVA: a=201 (above max) → invalid")
    void bva_a_above_max() {
        String result = Triangle.classify(201, 100, 100);
        assertTrue(result.contains("a"), "Should mention 'a'");
    }

    // =========================================================
    // BOUNDARY VALUE ANALYSIS — variable 'b'
    // =========================================================

    @Test @DisplayName("BVA: b=1 (min boundary)")
    void bva_b_min() {
        assertEquals("Isosceles", Triangle.classify(100, 1, 100));
    }

    @Test @DisplayName("BVA: b=2 (min+1)")
    void bva_b_min_plus1() {
        assertEquals("Isosceles", Triangle.classify(100, 2, 100));
    }

    @Test @DisplayName("BVA: b=199 (max-1)")
    void bva_b_max_minus1() {
        assertEquals("Isosceles", Triangle.classify(100, 199, 100));
    }

    @Test @DisplayName("BVA: b=200 (max boundary)")
    void bva_b_max() {
        assertEquals("Isosceles", Triangle.classify(101, 200, 101));
    }

    @Test @DisplayName("BVA: b=0 (below min) → invalid")
    void bva_b_below_min() {
        String result = Triangle.classify(100, 0, 100);
        assertTrue(result.contains("b"), "Should mention 'b'");
    }

    @Test @DisplayName("BVA: b=201 (above max) → invalid")
    void bva_b_above_max() {
        String result = Triangle.classify(100, 201, 100);
        assertTrue(result.contains("b"), "Should mention 'b'");
    }

    // =========================================================
    // BOUNDARY VALUE ANALYSIS — variable 'c'
    // =========================================================

    @Test @DisplayName("BVA: c=1 (min boundary)")
    void bva_c_min() {
        assertEquals("Isosceles", Triangle.classify(100, 100, 1));
    }

    @Test @DisplayName("BVA: c=2 (min+1)")
    void bva_c_min_plus1() {
        assertEquals("Isosceles", Triangle.classify(100, 100, 2));
    }

    @Test @DisplayName("BVA: c=199 (max-1)")
    void bva_c_max_minus1() {
        assertEquals("Isosceles", Triangle.classify(100, 100, 199));
    }

    @Test @DisplayName("BVA: c=200 (max boundary)")
    void bva_c_max() {
        assertEquals("Isosceles", Triangle.classify(101, 101, 200));
    }

    @Test @DisplayName("BVA: c=0 (below min) → invalid")
    void bva_c_below_min() {
        String result = Triangle.classify(100, 100, 0);
        assertTrue(result.contains("c"), "Should mention 'c'");
    }

    @Test @DisplayName("BVA: c=201 (above max) → invalid")
    void bva_c_above_max() {
        String result = Triangle.classify(100, 100, 201);
        assertTrue(result.contains("c"), "Should mention 'c'");
    }

    // =========================================================
    // EQUIVALENCE PARTITIONING — valid outputs
    // =========================================================

    @Test @DisplayName("EP: Equilateral triangle")
    void ep_equilateral() {
        assertEquals("Equilateral", Triangle.classify(5, 5, 5));
    }

    @Test @DisplayName("EP: Isosceles a==b")
    void ep_isosceles_ab() {
        assertEquals("Isosceles", Triangle.classify(5, 5, 8));
    }

    @Test @DisplayName("EP: Isosceles b==c")
    void ep_isosceles_bc() {
        assertEquals("Isosceles", Triangle.classify(8, 5, 5));
    }

    @Test @DisplayName("EP: Isosceles a==c")
    void ep_isosceles_ac() {
        assertEquals("Isosceles", Triangle.classify(5, 8, 5));
    }

    @Test @DisplayName("EP: Scalene triangle")
    void ep_scalene() {
        assertEquals("Scalene", Triangle.classify(3, 4, 5));
    }

    @Test @DisplayName("EP: NotATriangle — a >= b+c")
    void ep_not_a_triangle_a() {
        assertEquals("NotATriangle", Triangle.classify(10, 3, 4));
    }

    @Test @DisplayName("EP: NotATriangle — b >= a+c")
    void ep_not_a_triangle_b() {
        assertEquals("NotATriangle", Triangle.classify(3, 10, 4));
    }

    @Test @DisplayName("EP: NotATriangle — c >= a+b")
    void ep_not_a_triangle_c() {
        assertEquals("NotATriangle", Triangle.classify(3, 4, 10));
    }

    // =========================================================
    // BOUNDARY of triangle inequality (BVA on c4/c5/c6)
    // =========================================================

    @Test @DisplayName("BVA c4: a == b+c exactly → NotATriangle")
    void bva_c4_exact() {
        assertEquals("NotATriangle", Triangle.classify(7, 3, 4));
    }

    @Test @DisplayName("BVA c4: a == b+c-1 → valid Scalene")
    void bva_c4_just_valid() {
        assertEquals("Scalene", Triangle.classify(6, 3, 4));
    }

    @Test @DisplayName("BVA c5: b == a+c exactly → NotATriangle")
    void bva_c5_exact() {
        assertEquals("NotATriangle", Triangle.classify(3, 7, 4));
    }

    @Test @DisplayName("BVA c6: c == a+b exactly → NotATriangle")
    void bva_c6_exact() {
        assertEquals("NotATriangle", Triangle.classify(3, 4, 7));
    }

    // =========================================================
    // ERROR GUESSING
    // =========================================================

    @Test @DisplayName("EG: All sides = 1 (minimum equilateral)")
    void eg_all_ones() {
        assertEquals("Equilateral", Triangle.classify(1, 1, 1));
    }

    @Test @DisplayName("EG: All sides = 200 (maximum equilateral)")
    void eg_all_max() {
        assertEquals("Equilateral", Triangle.classify(200, 200, 200));
    }

    @Test @DisplayName("EG: Negative value for a")
    void eg_negative_a() {
        String result = Triangle.classify(-1, 5, 5);
        assertTrue(result.contains("a"));
    }

    @Test @DisplayName("EG: a=0, b=0, c=0 — all zeros")
    void eg_all_zeros() {
        // a fails first
        String result = Triangle.classify(0, 0, 0);
        assertTrue(result.contains("a"));
    }

    @Test @DisplayName("EG: Large scalene near boundary")
    void eg_large_scalene() {
        assertEquals("Scalene", Triangle.classify(198, 199, 200));
    }

    @Test @DisplayName("EG: Two sides sum equals third (degenerate)")
    void eg_degenerate_triangle() {
        assertEquals("NotATriangle", Triangle.classify(1, 1, 2));
    }

    @Test @DisplayName("EG: a=1, b=1, c=1 — smallest equilateral")
    void eg_smallest_equilateral() {
        assertEquals("Equilateral", Triangle.classify(1, 1, 1));
    }

    // =========================================================
    // BRANCH / CONDITION COVERAGE
    // =========================================================

    @Test @DisplayName("Branch: only a is invalid")
    void branch_only_a_invalid() {
        String result = Triangle.classify(0, 5, 5);
        assertFalse(result.equals("Equilateral") || result.equals("Isosceles")
                || result.equals("Scalene") || result.equals("NotATriangle"));
    }

    @Test @DisplayName("Branch: only b is invalid (a valid)")
    void branch_only_b_invalid() {
        String result = Triangle.classify(5, 0, 5);
        assertTrue(result.contains("b"));
    }

    @Test @DisplayName("Branch: only c is invalid (a,b valid)")
    void branch_only_c_invalid() {
        String result = Triangle.classify(5, 5, 0);
        assertTrue(result.contains("c"));
    }

    @Test @DisplayName("Branch: all conditions valid → Equilateral path")
    void branch_equilateral_path() {
        assertEquals("Equilateral", Triangle.classify(7, 7, 7));
    }

    @Test @DisplayName("Branch: isosceles path a==b, c different")
    void branch_isosceles_ab_path() {
        assertEquals("Isosceles", Triangle.classify(6, 6, 10));
    }

    @Test @DisplayName("Branch: isosceles path b==c, a different")
    void branch_isosceles_bc_path() {
        assertEquals("Isosceles", Triangle.classify(10, 6, 6));
    }

    @Test @DisplayName("Branch: isosceles path a==c, b different")
    void branch_isosceles_ac_path() {
        assertEquals("Isosceles", Triangle.classify(6, 10, 6));
    }

    @Test @DisplayName("Branch: scalene — no sides equal")
    void branch_scalene_path() {
        assertEquals("Scalene", Triangle.classify(5, 6, 7));
    }

    @Test @DisplayName("Branch: NotATriangle when a >= b+c")
    void branch_not_triangle_first_condition() {
        assertEquals("NotATriangle", Triangle.classify(100, 1, 1));
    }
}