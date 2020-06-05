import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.Repository;

public class ShopProperties {

    public static class ObjectProperties {
        protected static IRI address;
        protected static IRI belongsTo;
        protected static IRI category;
        protected static IRI contain;
        protected static IRI currency;
        protected static IRI price;
        protected static IRI providedFrom;
        protected static IRI salary;

        protected static IRI worksFor;
        protected static IRI manage; // extend worksFor

        protected static void createIRIs(Repository repository, String namespace) {
            ValueFactory valueFactory = repository.getValueFactory();

            address = valueFactory.createIRI(namespace, "address");
            belongsTo = valueFactory.createIRI(namespace, "belongsTo");
            category = valueFactory.createIRI(namespace, "category");
            contain = valueFactory.createIRI(namespace, "contain");
            currency = valueFactory.createIRI(namespace, "currency");
            manage = valueFactory.createIRI(namespace, "manage");
            price = valueFactory.createIRI(namespace, "price");
            providedFrom = valueFactory.createIRI(namespace, "providedFrom");
            salary = valueFactory.createIRI(namespace, "salary");
            worksFor = valueFactory.createIRI(namespace, "worksFor");
        }
    }

    public static class DataProperties {
        protected static IRI ISO_4217_code;
        protected static IRI ISO_4217_num_code;
        protected static IRI amount;
        protected static IRI city;
        protected static IRI code;
        protected static IRI employeeId;
        protected static IRI employeesNb;
        protected static IRI exponent;
        protected static IRI name;
        protected static IRI sectorName;
        protected static IRI street;
        protected static IRI symbol;
        protected static IRI validity;

        protected static void createIRIs(Repository repository, String namespace) {
            ValueFactory valueFactory = repository.getValueFactory();
            
            ShopProperties.DataProperties.amount = valueFactory.createIRI(namespace, "amount");
            ShopProperties.DataProperties.city = valueFactory.createIRI(namespace, "city");
            ShopProperties.DataProperties.code = valueFactory.createIRI(namespace, "code");
            ShopProperties.DataProperties.employeeId = valueFactory.createIRI(namespace, "employeeId");
            ShopProperties.DataProperties.employeesNb = valueFactory.createIRI(namespace, "employeesNb");
            ShopProperties.DataProperties.exponent = valueFactory.createIRI(namespace, "exponent");
            ShopProperties.DataProperties.ISO_4217_code = valueFactory.createIRI(namespace, "ISO_4217_code");
            ShopProperties.DataProperties.ISO_4217_num_code = valueFactory.createIRI(namespace, "ISO_4217_num_code");
            ShopProperties.DataProperties.name = valueFactory.createIRI(namespace, "name");
            ShopProperties.DataProperties.sectorName = valueFactory.createIRI(namespace, "sectorName");
            ShopProperties.DataProperties.street = valueFactory.createIRI(namespace, "street");
            ShopProperties.DataProperties.symbol = valueFactory.createIRI(namespace, "symbol");
            ShopProperties.DataProperties.validity = valueFactory.createIRI(namespace, "validity");
        }
    }
}
