package kr.team.ticketing.domain.image;

public enum ImageType {
	MAIN("메인사진"),
	THUMBNAIL("썸네일"),
	ETC("기타");

	private String name;

	ImageType(String name) {
		this.name = name;
	}
}
