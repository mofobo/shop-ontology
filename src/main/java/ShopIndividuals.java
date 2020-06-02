import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.Repository;

public class ShopIndividuals {
    // Amount
    public static IRI Salary1;
    public static IRI Salary2;
    public static IRI Salary3;
    public static IRI Price1;
    public static IRI Price2;
    public static IRI Price3;
    public static IRI Price4;

    // Distributor
    public static IRI Distributor1;
    public static IRI Distributor2;

    // Retailer
    public static IRI Retailer1;

    // Currency
    public static IRI SwissFranc;

    // Location
    public static IRI Location1;
    public static IRI Location2;
    public static IRI Location3;

    // Director
    public static IRI Albert;
    // Section-Chief
    public static IRI Beat;
    public static IRI Bernard;
    // Worker
    public static IRI Charlie;
    public static IRI Christian;

    // Product
    public static IRI Product1;
    public static IRI Product2;
    public static IRI Product3;
    public static IRI Product4;

    // Category
    public static IRI Category1;
    public static IRI Category2;
    public static IRI Category3;
    public static IRI Category4;

    // Section
    public static IRI BICYCLE;
    public static IRI FOOD;

    protected static void createIRIs(Repository repository, String namespace) {
        ValueFactory valueFactory = repository.getValueFactory();

        Salary1 = valueFactory.createIRI(namespace, "Salary1");
        Salary2 = valueFactory.createIRI(namespace, "Salary2");
        Salary3 = valueFactory.createIRI(namespace, "Salary3");

        Price1 = valueFactory.createIRI(namespace, "Price1");
        Price2 = valueFactory.createIRI(namespace, "Price2");
        Price3 = valueFactory.createIRI(namespace, "Price3");
        Price4 = valueFactory.createIRI(namespace, "Price4");

        Distributor1 = valueFactory.createIRI(namespace, "Distributor1");
        Distributor2 = valueFactory.createIRI(namespace, "Distributor2");

        Retailer1 = valueFactory.createIRI(namespace, "Retailer1");

        SwissFranc = valueFactory.createIRI(namespace, "SwissFranc");

        Location1 = valueFactory.createIRI(namespace, "Location1");
        Location2 = valueFactory.createIRI(namespace, "Location2");
        Location3 = valueFactory.createIRI(namespace, "Location3");

        Albert = valueFactory.createIRI(namespace, "Albert");
        Beat = valueFactory.createIRI(namespace, "Beat");
        Bernard = valueFactory.createIRI(namespace, "Bernard");
        Charlie = valueFactory.createIRI(namespace, "Charlie");
        Christian = valueFactory.createIRI(namespace, "Christian");

        Product1 = valueFactory.createIRI(namespace, "Product1");
        Product2 = valueFactory.createIRI(namespace, "Product2");
        Product3 = valueFactory.createIRI(namespace, "Product3");
        Product4 = valueFactory.createIRI(namespace, "Product4");

        Category1 = valueFactory.createIRI(namespace, "Category1");
        Category2 = valueFactory.createIRI(namespace, "Category2");
        Category3 = valueFactory.createIRI(namespace, "Category3");
        Category4 = valueFactory.createIRI(namespace, "Category4");

        BICYCLE = valueFactory.createIRI(namespace, "BICYCLE");
        FOOD = valueFactory.createIRI(namespace, "FOOD");
    }

}
