package lessonOne;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    private Person() {
    }
    public static Builder builder(){
        return new Builder();
    }

    @Override
    public String toString() {
        return "lessonOne.Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

    public static class Builder {

        private final Person person;

        public Builder() {
            this.person = new Person();
        }

        public Builder withFirstName(String firstName) {
            person.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            person.lastName = lastName;
            return this;
        }

        public Builder withMiddleName(String middleName) {
            person.middleName = middleName;
            return this;
        }

        public Builder withCountry(String country) {
            person.country = country;
            return this;
        }

        public Builder withAddress(String address) {
            person.address = address;
            return this;
        }

        public Builder withPhone(String phone) {
            person.phone = phone;
            return this;
        }

        public Builder withAge(int age) {
            person.age = age;
            return this;
        }

        public Builder withGender(String gender) {
            person.gender = gender;
            return this;
        }

        public Person build() {
            if (this.person.firstName == null || this.person.lastName == null || this.person.age == 0 || this.person.gender == null) {
                throw new IllegalStateException("Wrong person data");
            }
            return this.person;
        }

    }
}