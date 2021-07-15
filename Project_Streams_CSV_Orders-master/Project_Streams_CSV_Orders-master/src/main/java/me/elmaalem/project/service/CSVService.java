package me.elmaalem.project.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.elmaalem.project.repository.DBConfiguration;
import me.elmaalem.project.repository.PatientsRepo;

@Service
public class CSVService {

    @Autowired
    PatientsRepo patientRepo;
    @Autowired
    Connection conn = null;

    Statement stmt = null;

    public String getAllPatients() {
        try {
			conn = DBConfiguration.getConnection();
			stmt = conn.createStatement();

            String query = "select * from patients ";

            ResultSet rs = stmt.executeQuery(query);
            JSONObject jo = new JSONObject();
            JSONArray gen = convert(rs);

            jo.put("Patients",gen);

            return jo.toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public String getCountByCityAndSpec(String[] cities, String[] spec) {


        try {

            String citiesCondition = "";
            citiesCondition = "city in " + extractArguments(cities);

            String specCondition = "";
            specCondition = "pr.specialization in " + extractArguments(spec);
            
            String condition = "";

            if(!cities[0].equals("All") && !spec[0].equals("All")){
                condition = "where " + citiesCondition + " AND " + specCondition;
            }
            if(cities[0].equals("All")){
                condition = "where " +specCondition;
            }
            if(spec[0].equals("All")){
                condition = "where " +citiesCondition;
            }

            conn = DBConfiguration.getConnection();
            stmt = conn.createStatement();

            String query = "select firstName, lastName, city, count(v.visitId) as \"countVisits\""
                            +"from practitioners as pr " 
                            +"inner join patient2practitioner as p2p on  pr.practitionerId= p2p.practitionerId "
                            +"left join patients as p on p2p.patientId = p.patientId "
                            +"left join visits as v on p.patientId = v.patientId  " 
                            + condition
                            + " group by p.patientId";            

            ResultSet rs = stmt.executeQuery(query);
            
            JSONObject jo = new JSONObject();
            JSONArray gen = convert(rs);

            jo.put("Patients",gen);

            return jo.toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
	}
    public String getAllPatientsByCity(String [] cities) {

        try {
            conn = DBConfiguration.getConnection();
            stmt = conn.createStatement();

            String citiesCondition = "";
            if(! cities[0].equals("All")){
                citiesCondition = "where city in " + extractArguments(cities);
            }


            String query = "select firstName, lastName, city, count(v.visitId) as \"countVisits\""
                            +"from patients as p "
                            +"left join visits as v on p.patientId = v.patientId "  
                            + citiesCondition
                            + "group by p.patientId";            

            ResultSet rs = stmt.executeQuery(query);
            
            JSONObject jo = new JSONObject();
            JSONArray gen = convert(rs);

            jo.put("Patients",gen);

            return jo.toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public String getAllPatientsBySpec(String [] spec) {

        try {
			conn = DBConfiguration.getConnection();
            stmt = conn.createStatement();

            String specCondition = "";
            if(! spec[0].equals("All")){
                specCondition = "where pr.specialization in  " + extractArguments(spec);
            }

			String query = "select firstName, lastName, count(v.visitId) as \"countVisits\" "
                            +"from practitioners as pr " 
                            +"inner join patient2practitioner as p2p on  pr.practitionerId= p2p.practitionerId "
                            +"left join patients as p on p2p.patientId = p.patientId "
                            +"left join visits as v on p.patientId = v.patientId  "
                            + specCondition
                            + " group by p.patientId"; 

            ResultSet rs = stmt.executeQuery(query);
            JSONObject jo = new JSONObject();
            JSONArray gen = convert(rs);

            jo.put("Patients",gen);

            return jo.toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

	public String getCountForSpec(String[] spec) {
       try {
			conn = DBConfiguration.getConnection();
            stmt = conn.createStatement();
            
            String specCondition = "";
            if(! spec[0].equals("All")){
                specCondition = "where pr.specialization in  " + extractArguments(spec);
            }
            
			String query = "select specialization, count(v.visitId) as \"numberOfVists\"  "
                            +"from practitioners as pr " 
                            +"left join visits as v on pr.practitionerId= v.practitionerId "
                            + specCondition
                            + " group by pr.practitionerId";            

            ResultSet rs = stmt.executeQuery(query);
            JSONObject jo = new JSONObject();
            JSONArray gen = convert(rs);

            jo.put("Visits",gen);

            return jo.toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static String extractArguments( String[] args ){

        StringBuffer sb = new StringBuffer();
        sb.append("('");

        for(int i = 0; i < args.length; i++) {

           sb.append(args[i]);

           if ( i + 1 < args.length)
                sb.append("', '");

        }
        sb.append("\')");

        return sb.toString();

    }

    public static JSONArray convert( ResultSet rs )
        throws SQLException, JSONException
    {
        JSONArray json = new JSONArray();
        ResultSetMetaData rsmd = rs.getMetaData();

        while(rs.next()) {
        int numColumns = rsmd.getColumnCount();
        JSONObject obj = new JSONObject();

        for (int i=1; i<numColumns+1; i++) {
            String column_name = rsmd.getColumnName(i);

            if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
            obj.put(column_name, rs.getArray(column_name));
            }
            else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
            obj.put(column_name, rs.getInt(column_name));
            }
            else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
            obj.put(column_name, rs.getBoolean(column_name));
            }
            else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
            obj.put(column_name, rs.getBlob(column_name));
            }
            else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
            obj.put(column_name, rs.getDouble(column_name)); 
            }
            else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
            obj.put(column_name, rs.getFloat(column_name));
            }
            else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
            obj.put(column_name, rs.getInt(column_name));
            }
            else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
            obj.put(column_name, rs.getNString(column_name));
            }
            else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
            obj.put(column_name, rs.getString(column_name));
            }
            else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
            obj.put(column_name, rs.getInt(column_name));
            }
            else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
            obj.put(column_name, rs.getInt(column_name));
            }
            else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
            obj.put(column_name, rs.getDate(column_name));
            }
            else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
            obj.put(column_name, rs.getTimestamp(column_name));   
            }
            else{
            obj.put(column_name, rs.getObject(column_name));
            }
        }

        json.put(obj);
    };

    return json;
  }




}

