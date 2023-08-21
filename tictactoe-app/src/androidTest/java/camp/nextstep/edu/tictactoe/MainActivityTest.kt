package camp.nextstep.edu.tictactoe

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun click_top_left() {
        // when: 'LEFT TOP' 버튼을 누르면
        onView(withId(R.id.cell_top_left)).perform(click())

        // then: 'X가 보여야 한다
        onView(withId(R.id.cell_top_left)).check(matches(DrawableMatcher(R.drawable.ic_x_black)))
    }

    @Test
    fun click_top_left_and_middle() {
        // when: 'LEFT TOP' 버튼을 누르면
        onView(withId(R.id.cell_top_left)).perform(click())
        onView(withId(R.id.cell_middle)).perform(click())

        // then: 'X가 보여야 한다
        onView(withId(R.id.cell_middle)).check(matches(DrawableMatcher(R.drawable.ic_o_black)))
    }
}
