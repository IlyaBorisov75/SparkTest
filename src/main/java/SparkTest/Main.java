package SparkTest;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import models.Driver;
import models.Trip;
import scala.Tuple2;



public class Main {

	public static void main(String[] args) { 
        JavaSparkContext sc = new JavaSparkContext(new SparkConf()
        		.setAppName("Taxi")
        		.setMaster("local")
        		);
       JavaRDD<String> tripLiens = sc.textFile("data/taxi/taxi_order.txt");
       JavaRDD<String> driverLiens = sc.textFile("data/taxi/drivers.txt");
       
       //Task 1
       System.out.println(tripLiens.count());
       
       JavaRDD<Trip> tripFields = tripLiens.map(Trip::fromString);
       tripFields.cache();
       
       //Task 2
       double numberOfBostonTrips = tripFields
    		   .filter((x)->x.getCity().equals("Boston"))
    		   .filter((x)->x.getLength()>10)
    		   .count();
       System.out.println(numberOfBostonTrips);
       
       //Task 3
       double sumOfBostonTrips = tripFields.filter((x)->x.getCity().equals("Boston"))
    		   .mapToDouble(x->x.getLength())
    		   .reduce((s1, s2)->s1 + s2);
       System.out.println(sumOfBostonTrips);
       
       //Task 4
       JavaRDD<Driver> driverFields = driverLiens.map(Driver::fromString);
       
       JavaPairRDD<String, String> driverStatistic = driverFields.mapToPair(x -> new Tuple2<>(x.getId(), x.getDriverName()));
       JavaPairRDD<String, Integer> tripLenhth= tripFields.mapToPair(x -> new Tuple2<>(x.getId(), x.getLength())).reduceByKey(Integer::sum);
       
       driverStatistic
       		.join(tripLenhth)
       		.mapToPair(x->x._2.swap())
       		.sortByKey(false)
       		.take(3)
       		.forEach(System.out::println);
    }
}








