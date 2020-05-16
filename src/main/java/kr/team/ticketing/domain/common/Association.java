package kr.team.ticketing.domain.common;

public class Association<T> {
	private final long id;

	public Association(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
}
