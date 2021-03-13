package commons;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelperFacker {

	private Locale locale = new Locale("en");
	private Faker faker = new Faker(locale);

	public static DataHelperFacker getData() {
		return new DataHelperFacker();
	}

	public String getFirstName() {
		return faker.name().firstName();
	}

	public String getLastName() {
		return faker.name().lastName();
	}

	public String getAddress() {
		return faker.address().fullAddress();
	}

	public String getPhone() {
		return faker.phoneNumber().phoneNumber();
	}

	public String getCity() {
		return faker.address().cityName();
	}

	public String getEmail() {
		return faker.internet().emailAddress();
	}

	public String getCompanyName() {
		return faker.company().name();
	}

	public String getPassword() {
		return faker.internet().password(6, 8);
	}
}
