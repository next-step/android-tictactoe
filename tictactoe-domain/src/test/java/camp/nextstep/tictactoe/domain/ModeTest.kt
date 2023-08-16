package camp.nextstep.tictactoe.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ModeTest {

	private lateinit var mode: Mode

	@Before
	fun setUp() {
		mode = Mode.TwoPerson
	}

	@Test
	fun `첫번째 Player 를 가져오면, 첫번째 Player 가 반환된다`() {
		// when
		val actual = mode.getFirst()

		// then
		assertThat(actual).isInstanceOf(Player.Person::class.java)
		assertThat(actual.marker).isEqualTo(Marker.X)
	}

	@Test
	fun `현재 Player 가 첫번째 Player 일 때, 다음 Player 를 가져오면, 두번째 Player 가 반환된다`() {
		// given
		val firstPlayer = mode.getFirst()

		// when
		val actual = mode.getNext(firstPlayer)

		// then
		assertThat(actual).isInstanceOf(Player.Person::class.java)
		assertThat(actual.marker).isEqualTo(Marker.O)
	}

	@Test
	fun `현재 Player 가 두번째 Player 일 때, 다음 Player 를 가져오면, 첫번째 Player 가 반환된다`() {
		// given
		val firstPlayer = mode.getFirst()
		val secondPlayer = mode.getNext(firstPlayer)

		// when
		val actual = mode.getNext(secondPlayer)

		// then
		assertThat(actual).isInstanceOf(Player.Person::class.java)
		assertThat(actual.marker).isEqualTo(Marker.X)
	}
}