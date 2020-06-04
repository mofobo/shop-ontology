import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

public class SparqlQueries {
    static void execQueryGetEmployeesName(Repository rep) {
        System.out.println("\nGet all employees name.");
        RepositoryConnection conn = rep.getConnection();
        try {
            String queryString =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                            "PREFIX ns: <" + Main.NAMESPACE + ">" +
                            "select distinct ?name where { " +
                            "?employeeSpecialization rdfs:subClassOf ns:Employee . " +
                            "?employee rdf:type ?employeeSpecialization . " +
                            "?employee ns:name ?name . " +
                            "}";
            System.out.println(queryString);
            TupleQuery query = conn.prepareTupleQuery(queryString);

            try (TupleQueryResult result = query.evaluate()) {
                while (result.hasNext()) {
                    BindingSet solution = result.next();
                    String line = "?employee = " + solution.getValue("name");
                    System.out.println(line);
                }
            }
        } finally {
            conn.close();
        }
    }

    static void execQueryGetEmployeesNameWithSalaryMoreThan5k(Repository rep) {
        System.out.println("\nGet employees name with salary higher than 5000.");
        try (RepositoryConnection conn = rep.getConnection()) {
            String queryString =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                            "PREFIX ns: <" + Main.NAMESPACE + ">" +
                            "select distinct ?employeeName where { " +
                            "?employee ns:salary ?salary . " +
                            "?salary ns:amount ?amount . " +
                            "FILTER (?amount > 500000) ." +
                            "?employee ns:name ?employeeName ." +
                            "}";
            System.out.println(queryString);
            TupleQuery query = conn.prepareTupleQuery(queryString);

            try (TupleQueryResult result = query.evaluate()) {
                while (result.hasNext()) {
                    BindingSet solution = result.next();
                    String line = "?employeeName = " + solution.getValue("employeeName");
                    System.out.println(line);
                }
            }
        }
    }

    static void execQueryGetAllDistributorNamesAndTheirLocations(Repository rep) {
        System.out.println("\nGet all distributor names and their locations.");
        RepositoryConnection conn = rep.getConnection();
        try {
            String queryString =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                            "PREFIX ns: <" + Main.NAMESPACE + ">" +
                            "select distinct ?name ?location where { " +
                            "?distributor rdf:type ns:Distributor . " +
                            "?distributor ns:name ?name . " +
                            "OPTIONAL{?distributor ns:address ?location}" +
                            "}";

            System.out.println(queryString);
            TupleQuery query = conn.prepareTupleQuery(queryString);

            try (TupleQueryResult result = query.evaluate()) {
                while (result.hasNext()) {
                    BindingSet solution = result.next();
                    System.out.println("?name= " + solution.getValue("name") + " ,?location=" + solution.getValue("location"));
                }
            }
        } finally {
            conn.close();
        }
    }

    static void execQueryGetAllTandemBikeAndAppleProductNames(Repository rep) {
        System.out.println("\nGet all products from categories 'Apple' and 'Tandem Bike'.");
        RepositoryConnection conn = rep.getConnection();

        try {
            String queryString =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                            "PREFIX ns: <" + Main.NAMESPACE + ">" +
                            "select distinct ?name where { " +
                            "?product rdf:type ns:Product . " +
                            "{?product ns:category ns:Category1} UNION {?product ns:category ns:Category4}" +
                            "?product ns:name ?name ." +
                            "}";

            System.out.println(queryString);
            TupleQuery query = conn.prepareTupleQuery(queryString);

            try (TupleQueryResult result = query.evaluate()) {
                while (result.hasNext()) {
                    BindingSet solution = result.next();
                    System.out.println("?name= " + solution.getValue("name"));
                }
            }
        } finally {
            conn.close();
        }
    }

    static void execQueryGetAllProductsOrderedByDescendingPrice(Repository rep) {
        System.out.println("\nGet all products ordered by descending price.");
        RepositoryConnection conn = rep.getConnection();

        try {
            String queryString =
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                            "PREFIX ns: <" + Main.NAMESPACE + ">" +
                            "select distinct ?name ?amnt where { " +
                            "?product rdf:type ns:Product . " +
                            "?product ns:name ?name . " +
                            "?product ns:price ?price . " +
                            "?price ns:amount ?amnt ." +
                            "} ORDER BY DESC(?amnt)";

            System.out.println(queryString);
            TupleQuery query = conn.prepareTupleQuery(queryString);

            try (TupleQueryResult result = query.evaluate()) {
                while (result.hasNext()) {
                    BindingSet solution = result.next();
                    System.out.println("?name= " + solution.getValue("name")+" ?amnt=" + solution.getValue("amnt"));
                }
            }
        } finally {
            conn.close();
        }
    }
}
