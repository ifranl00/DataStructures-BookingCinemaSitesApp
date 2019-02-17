package ule.edi.model;

public class Person {

	private String name;
	private String nif;
	
	private int age;
	
	

	public Person(String name, String nif, int edad) {
        this.nif=nif;
		this.name = name;
		this.age = edad;
		
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "{ NIF: "+ nif + "  Name : " + name + ", Age:" + age + "}";
	}
	
    @Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		// Dos personas son iguales si son iguales sus nifs
    	
    	boolean isSamePerson = false;
    	
    	if(this == obj) { //Para saber si apuntan al mismo 
    		
    		isSamePerson = true;
    	}
		
    	if(obj instanceof Person) { //Para saber si los objetos pertenecen a la misma clase
    		
    		Person other = (Person)obj; //Usamos un casting para poder acceder a los atributos del objeto
    		
    		if(this.nif.equals(other.nif)) {
    			
    			isSamePerson = true;
    		}
    	}
		return isSamePerson;
	}
	
}
