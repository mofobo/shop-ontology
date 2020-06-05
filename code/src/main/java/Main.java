import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.model.vocabulary.XMLSchema;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.sail.memory.MemoryStore;


public class Main {

    public static final String NAMESPACE = "https://mofobo.ch/";

    public static void main(String[] args) {
        Repository repository = new SailRepository(new MemoryStore());

        buildOntology(repository);
        createIndividuals(repository);

        SparqlQueries.execQueryGetEmployeesName(repository);
        SparqlQueries.execQueryGetEmployeesNameWithSalaryMoreThan5k(repository);
        SparqlQueries.execQueryGetAllDistributorNamesAndTheirLocations(repository);
        SparqlQueries.execQueryGetAllTandemBikeAndAppleProductNames(repository);
        SparqlQueries.execQueryGetAllProductsOrderedByDescendingPrice(repository);
    }

    static void buildOntology(Repository repository) {

        ShopClasses.createIRIs(repository, NAMESPACE);
        ShopProperties.ObjectProperties.createIRIs(repository, NAMESPACE);
        ShopProperties.DataProperties.createIRIs(repository, NAMESPACE);

        RepositoryConnection conn = repository.getConnection();
        try {
            conn.add(ShopClasses.Amount, RDF.TYPE, RDFS.CLASS);
            conn.add(ShopClasses.Company, RDF.TYPE, RDFS.CLASS);
            conn.add(ShopClasses.Distributor, RDF.TYPE, RDFS.CLASS);
            conn.add(ShopClasses.Distributor, RDFS.SUBCLASSOF, ShopClasses.Company);
            conn.add(ShopClasses.Retailer, RDF.TYPE, RDFS.CLASS);
            conn.add(ShopClasses.Retailer, RDFS.SUBCLASSOF, ShopClasses.Company);
            conn.add(ShopClasses.Employee, RDFS.SUBCLASSOF, FOAF.PERSON);
            conn.add(ShopClasses.Currency, RDF.TYPE, RDFS.CLASS);
            conn.add(ShopClasses.Location, RDF.TYPE, RDFS.CLASS);
            conn.add(ShopClasses.Employee, RDF.TYPE, RDFS.CLASS);
            conn.add(ShopClasses.Employee, RDFS.SUBCLASSOF, FOAF.PERSON);
            conn.add(ShopClasses.Director, RDF.TYPE, RDFS.CLASS);
            conn.add(ShopClasses.Director, RDFS.SUBCLASSOF, ShopClasses.Employee);
            conn.add(ShopClasses.Section_Chief, RDF.TYPE, RDFS.CLASS);
            conn.add(ShopClasses.Section_Chief, RDFS.SUBCLASSOF, ShopClasses.Employee);
            conn.add(ShopClasses.Worker, RDF.TYPE, RDFS.CLASS);
            conn.add(ShopClasses.Worker, RDFS.SUBCLASSOF, ShopClasses.Employee);
            conn.add(ShopClasses.Product, RDF.TYPE, RDFS.CLASS);
            conn.add(ShopClasses.Product_Category, RDF.TYPE, RDFS.CLASS);
            conn.add(ShopClasses.Section, RDF.TYPE, RDFS.CLASS);

            conn.add(ShopProperties.ObjectProperties.address, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.ObjectProperties.belongsTo, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.ObjectProperties.category, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.ObjectProperties.contain, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.ObjectProperties.currency, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.ObjectProperties.manage, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.ObjectProperties.price, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.ObjectProperties.providedFrom, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.ObjectProperties.salary, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.ObjectProperties.worksFor, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.ObjectProperties.manage, RDFS.SUBPROPERTYOF, ShopProperties.ObjectProperties.worksFor);

            conn.add(ShopProperties.DataProperties.amount, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.DataProperties.city, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.DataProperties.code, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.DataProperties.employeeId, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.DataProperties.employeesNb, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.DataProperties.exponent, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.DataProperties.ISO_4217_code, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.DataProperties.ISO_4217_num_code, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.DataProperties.name, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.DataProperties.sectorName, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.DataProperties.street, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.DataProperties.symbol, RDF.TYPE, RDF.PREDICATE);
            conn.add(ShopProperties.DataProperties.validity, RDF.TYPE, RDF.PREDICATE);
        } finally {
            conn.close();
        }
    }

    static void createIndividuals(Repository repository) {
        ShopIndividuals.createIRIs(repository, NAMESPACE);

        createAmount(repository, ShopIndividuals.Salary1, 700000, ShopIndividuals.SwissFranc);
        createAmount(repository, ShopIndividuals.Salary2, 580000, ShopIndividuals.SwissFranc);
        createAmount(repository, ShopIndividuals.Salary3, 420000, ShopIndividuals.SwissFranc);

        createAmount(repository, ShopIndividuals.Price1, 200, ShopIndividuals.SwissFranc);
        createAmount(repository, ShopIndividuals.Price2, 450, ShopIndividuals.SwissFranc);
        createAmount(repository, ShopIndividuals.Price3, 120000, ShopIndividuals.SwissFranc);
        createAmount(repository, ShopIndividuals.Price4, 240000, ShopIndividuals.SwissFranc);

        createDistributor(repository, ShopIndividuals.Distributor1, "ALIGRO SA", 234, null);
        createDistributor(repository, ShopIndividuals.Distributor2, "ALL SPORTS SA", 123, ShopIndividuals.Location3);

        createRetailer(repository, ShopIndividuals.Retailer1, "Au Bon Prix", 53, "Public");

        createCurrency(repository, ShopIndividuals.SwissFranc, "CHF", 756, "CHF", 2);

        createLocation(repository, ShopIndividuals.Location1, "Markstrasse 3", "3007 Bern");
        createLocation(repository, ShopIndividuals.Location2, "Alpenstrasse 2a", "3006 Bern");
        createLocation(repository, ShopIndividuals.Location3, "Industriestrasse 1", "3018 Bern");

        createDirector(repository, ShopIndividuals.Albert, "Albert", "A_001", ShopIndividuals.Salary1, ShopIndividuals.Retailer1, ShopIndividuals.Location1);

        createSectionChief(repository, ShopIndividuals.Beat, "Beat", "B_001", ShopIndividuals.Salary2, ShopIndividuals.BICYCLE);
        createSectionChief(repository, ShopIndividuals.Bernard, "Bernard", "B_002", ShopIndividuals.Salary2, ShopIndividuals.FOOD);

        createWorker(repository, ShopIndividuals.Charlie, "Charlie", "C_001", ShopIndividuals.Salary3, ShopIndividuals.Retailer1);
        createWorker(repository, ShopIndividuals.Christian, "Christian", "C_002", ShopIndividuals.Salary3, ShopIndividuals.Retailer1);

        createProduct(repository, ShopIndividuals.Product1, "Golden Delicious", "F_001", "2020-06-06", ShopIndividuals.Category1, ShopIndividuals.Distributor1,
                ShopIndividuals.Price1);
        createProduct(repository, ShopIndividuals.Product2, "Tortellini", "C_001", "2020-07-01", ShopIndividuals.Category2, ShopIndividuals.Distributor1, ShopIndividuals.Price2);
        createProduct(repository, ShopIndividuals.Product3, "California threesixty", "B_001", "9999-01-01", ShopIndividuals.Category3, ShopIndividuals.Distributor2,
                ShopIndividuals.Price3);
        createProduct(repository, ShopIndividuals.Product4, "Miscargi Island", "T_001", "9999-01-01", ShopIndividuals.Category4, ShopIndividuals.Distributor2,
                ShopIndividuals.Price4);

        createProductCategory(repository, ShopIndividuals.Category1, "Apple");
        createProductCategory(repository, ShopIndividuals.Category2, "Pasta");
        createProductCategory(repository, ShopIndividuals.Category3, "BMX Bike");
        createProductCategory(repository, ShopIndividuals.Category4, "Tandem Bike");

        createSection(repository, ShopIndividuals.FOOD, ShopIndividuals.Retailer1, ShopIndividuals.Product1, ShopIndividuals.Product2);
        createSection(repository, ShopIndividuals.BICYCLE, ShopIndividuals.Retailer1, ShopIndividuals.Product3, ShopIndividuals.Product4);
    }

    static void createAmount(Repository repository, IRI iri, int amount, IRI currency) {
        RepositoryConnection conn = repository.getConnection();
        ValueFactory f = repository.getValueFactory();
        try {
            conn.add(iri, RDF.TYPE, ShopClasses.Amount);
            conn.add(iri, ShopProperties.DataProperties.amount, f.createLiteral(String.valueOf(amount), XMLSchema.INTEGER));
            conn.add(iri, ShopProperties.ObjectProperties.currency, currency);
        } finally {
            conn.close();
        }
    }

    static void createDistributor(Repository repository, IRI iri, String name, int employeesNb, IRI address) {
        RepositoryConnection conn = repository.getConnection();
        ValueFactory f = repository.getValueFactory();
        try {
            conn.add(iri, RDF.TYPE, ShopClasses.Distributor);
            conn.add(iri, ShopProperties.DataProperties.name, f.createLiteral(name, XMLSchema.STRING));
            conn.add(iri, ShopProperties.DataProperties.employeesNb, f.createLiteral(String.valueOf(employeesNb), XMLSchema.INTEGER));
            if (address != null) conn.add(iri, ShopProperties.ObjectProperties.address, address);
        } finally {
            conn.close();
        }
    }

    static void createRetailer(Repository repository, IRI iri, String name, int employeesNb, String sector) {
        RepositoryConnection conn = repository.getConnection();
        ValueFactory f = repository.getValueFactory();
        try {
            conn.add(iri, RDF.TYPE, ShopClasses.Retailer);
            conn.add(iri, ShopProperties.DataProperties.name, f.createLiteral(name, XMLSchema.STRING));
            conn.add(iri, ShopProperties.DataProperties.employeesNb, f.createLiteral(String.valueOf(employeesNb), XMLSchema.INTEGER));
            conn.add(iri, ShopProperties.DataProperties.name, f.createLiteral(sector, XMLSchema.STRING));
        } finally {
            conn.close();
        }
    }

    static void createCurrency(Repository repository, IRI iri, String iso4217Code, int iso4217NumCode, String symbol, int exponent) {
        RepositoryConnection conn = repository.getConnection();
        ValueFactory f = repository.getValueFactory();
        try {
            conn.add(iri, RDF.TYPE, ShopClasses.Currency);
            conn.add(iri, ShopProperties.DataProperties.ISO_4217_code, f.createLiteral(iso4217Code, XMLSchema.STRING));
            conn.add(iri, ShopProperties.DataProperties.ISO_4217_num_code, f.createLiteral(String.valueOf(iso4217NumCode), XMLSchema.INTEGER));
            conn.add(iri, ShopProperties.DataProperties.symbol, f.createLiteral(symbol, XMLSchema.STRING));
            conn.add(iri, ShopProperties.DataProperties.exponent, f.createLiteral(String.valueOf(exponent), XMLSchema.INTEGER));
        } finally {
            conn.close();
        }
    }

    static void createLocation(Repository repository, IRI iri, String street, String city) {
        RepositoryConnection conn = repository.getConnection();
        ValueFactory f = repository.getValueFactory();
        try {
            conn.add(iri, RDF.TYPE, ShopClasses.Location);
            conn.add(iri, ShopProperties.DataProperties.street, f.createLiteral(street, XMLSchema.STRING));
            conn.add(iri, ShopProperties.DataProperties.city, f.createLiteral(city, XMLSchema.STRING));
        } finally {
            conn.close();
        }
    }

    static void createDirector(Repository repository, IRI iri, String name, String employeeId, IRI salary, IRI retailer, IRI address) {
        RepositoryConnection conn = repository.getConnection();
        ValueFactory f = repository.getValueFactory();
        try {
            conn.add(iri, RDF.TYPE, ShopClasses.Director);
            conn.add(iri, ShopProperties.DataProperties.name, f.createLiteral(name, XMLSchema.STRING));
            conn.add(iri, ShopProperties.DataProperties.employeeId, f.createLiteral(employeeId, XMLSchema.STRING));
            conn.add(iri, ShopProperties.ObjectProperties.salary, salary);
            conn.add(iri, ShopProperties.ObjectProperties.manage, retailer);
            conn.add(iri, ShopProperties.ObjectProperties.address, address);
        } finally {
            conn.close();
        }
    }

    static void createSectionChief(Repository repository, IRI iri, String name, String employeeId, IRI salary, IRI section) {
        RepositoryConnection conn = repository.getConnection();
        ValueFactory f = repository.getValueFactory();
        try {
            conn.add(iri, RDF.TYPE, ShopClasses.Section_Chief);
            conn.add(iri, ShopProperties.DataProperties.name, f.createLiteral(name, XMLSchema.STRING));
            conn.add(iri, ShopProperties.DataProperties.employeeId, f.createLiteral(employeeId, XMLSchema.STRING));
            conn.add(iri, ShopProperties.ObjectProperties.salary, salary);
            conn.add(iri, ShopProperties.ObjectProperties.manage, section);
        } finally {
            conn.close();
        }
    }

    static void createWorker(Repository repository, IRI iri, String name, String employeeId, IRI salary, IRI retailer) {
        RepositoryConnection conn = repository.getConnection();
        ValueFactory f = repository.getValueFactory();
        try {
            conn.add(iri, RDF.TYPE, ShopClasses.Worker);
            conn.add(iri, ShopProperties.DataProperties.name, f.createLiteral(name, XMLSchema.STRING));
            conn.add(iri, ShopProperties.DataProperties.employeeId, f.createLiteral(employeeId, XMLSchema.STRING));
            conn.add(iri, ShopProperties.ObjectProperties.salary, salary);
            conn.add(iri, ShopProperties.ObjectProperties.worksFor, retailer);
        } finally {
            conn.close();
        }
    }

    static void createProduct(Repository repository, IRI iri, String name, String code, String validity, IRI category, IRI distributor, IRI price) {
        RepositoryConnection conn = repository.getConnection();
        ValueFactory f = repository.getValueFactory();
        try {
            conn.add(iri, RDF.TYPE, ShopClasses.Product);
            conn.add(iri, ShopProperties.DataProperties.name, f.createLiteral(name, XMLSchema.STRING));
            conn.add(iri, ShopProperties.DataProperties.code, f.createLiteral(code, XMLSchema.STRING));
            conn.add(iri, ShopProperties.DataProperties.validity, f.createLiteral(validity, XMLSchema.DATETIME));
            conn.add(iri, ShopProperties.ObjectProperties.category, category);
            conn.add(iri, ShopProperties.ObjectProperties.providedFrom, distributor);
            conn.add(iri, ShopProperties.ObjectProperties.price, price);
        } finally {
            conn.close();
        }
    }

    static void createProductCategory(Repository repository, IRI iri, String name) {
        RepositoryConnection conn = repository.getConnection();
        ValueFactory f = repository.getValueFactory();
        try {
            conn.add(iri, RDF.TYPE, ShopClasses.Product_Category);
            conn.add(iri, ShopProperties.DataProperties.name, f.createLiteral(name, XMLSchema.STRING));
        } finally {
            conn.close();
        }
    }

    static void createSection(Repository repository, IRI iri, IRI retailer, IRI... products) {
        RepositoryConnection conn = repository.getConnection();
        ValueFactory f = repository.getValueFactory();
        try {
            conn.add(iri, RDF.TYPE, ShopClasses.Section);
            conn.add(iri, ShopProperties.ObjectProperties.belongsTo, retailer);
            if (products != null) {
                for (IRI product : products) {
                    conn.add(iri, ShopProperties.ObjectProperties.contain, product);
                }
            }
        } finally {
            conn.close();
        }
    }
}
