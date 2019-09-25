package by.itClass.model.enums;

public class SectionManager {
	
	public static Enum<?> getSectionKind(String param){
		try {
			return SectionKind.valueOf(param.toUpperCase());
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
			return SectionKind.TODAY;
		}
	}
}
