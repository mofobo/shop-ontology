import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.Repository;

public class ShopClasses {
    public static IRI Amount;
    
    public static IRI Company;
    public static IRI Distributor;   // extend Company
    public static IRI Retailer;      // extend Company
    
    public static IRI Currency;
    public static IRI Location;

    public static IRI Employee;      // extend foaf:Person
    public static IRI Director;      // extend Employee
    public static IRI Section_Chief; // extend Employee
    public static IRI Worker;        // extend Employee
    
    public static IRI Product;
    public static IRI Product_Category;
    public static IRI Section;

    protected static void createIRIs(Repository repository, String namespace) {
        ValueFactory valueFactory = repository.getValueFactory();

        Amount = valueFactory.createIRI(namespace, "Amount");
        Company = valueFactory.createIRI(namespace, "Company");
        Distributor = valueFactory.createIRI(namespace, "Distributor");
        Retailer = valueFactory.createIRI(namespace, "Retailer");
        Currency = valueFactory.createIRI(namespace, "Currency");
        Location = valueFactory.createIRI(namespace, "Location");
        Employee = valueFactory.createIRI(namespace, "Employee");
        Director = valueFactory.createIRI(namespace, "Director");
        Section_Chief = valueFactory.createIRI(namespace, "Section_Chief");
        Worker = valueFactory.createIRI(namespace, "Worker");
        Product = valueFactory.createIRI(namespace, "Product");
        Product_Category = valueFactory.createIRI(namespace, "Product_Category");
        Section = valueFactory.createIRI(namespace, "Section");
    }
    
}
