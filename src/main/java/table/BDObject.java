package table;

import cnx.Connex;

import java.sql.*;
import java.lang.reflect.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BDObject {
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BDObject(){

	}

// -------------------------------- FONCTION SELECT --------------------------------------
	public BDObject createInstancefromDB(Connection conn, String id) throws Exception{
		if(conn.isClosed()){
			conn = Connex.OracleConnect();
		}
		BDObject retour = null;

		Class<?> thisclass = Class.forName(this.getClass().getName());
		Constructor<?> hisConstr = thisclass.getConstructor();
		ArrayList<Field> vField = new ArrayList<Field>();
		for(Field f : thisclass.getDeclaredFields()){
			if(f.getModifiers() == 0){
				vField.add(f);
			}
		}
		String idField = vField.get(0).getName();

		Statement stmt = conn.createStatement();
		String query = "SELECT * FROM "+this.getClass().getSimpleName()+" WHERE "+idField+" ='"+id+"'";
		System.out.println("Query >> "+query);
		ResultSet res = stmt.executeQuery(query);
		while(res.next()){
			Object clone = hisConstr.newInstance();
			for(int i=0;i<vField.size();i++){
				DynamicInsert(clone,res,vField.get(i),i+1);
			}
			retour = (BDObject) clone;
		}
		res.close();
		stmt.close();

		return retour;
	}

	public BDObject createInstancefromDB(Connection conn, String colonne, Object value) throws Exception{
		if(conn.isClosed()){
			conn = Connex.OracleConnect();
		}
		BDObject retour = null;

		Class<?> thisclass = Class.forName(this.getClass().getName());
		Constructor<?> hisConstr = thisclass.getConstructor();
		ArrayList<Field> vField = new ArrayList<Field>();
		for(Field f : thisclass.getDeclaredFields()){
			if(f.getModifiers() == 0){
				vField.add(f);
			}
		}

		Statement stmt = conn.createStatement();
		String query = "SELECT * FROM "+this.getClass().getSimpleName()+" WHERE "+colonne+" ='"+value+"'";
		System.out.println("Query >> "+query);
		ResultSet res = stmt.executeQuery(query);
		while(res.next()){
			Object clone = hisConstr.newInstance();
			for(int i=0;i<vField.size();i++){
				DynamicInsert(clone,res,vField.get(i),i+1);
			}
			retour = (BDObject) clone;
		}
		res.close();
		stmt.close();

		return retour;
	}

	public ArrayList<BDObject> Find(Connection conn) throws Exception{
		if(conn.isClosed()){
			conn = Connex.OracleConnect();
		}

		ArrayList<BDObject> tab = new ArrayList<BDObject>();

		Class<?> thisclass = Class.forName(this.getClass().getName());
		Constructor<?> hisConstr = thisclass.getConstructor();
		ArrayList<Field> vField = new ArrayList<Field>();
		for(Field f : thisclass.getDeclaredFields()){
			if(f.getModifiers() == 0){
				vField.add(f);
			}
		}
		Statement stmt = conn.createStatement();
		String query = "SELECT * FROM "+this.getClass().getSimpleName()+" ORDER BY "+getAttributSimpleName(vField.get(0));

		System.out.println(query);

		ResultSet res = stmt.executeQuery(query);
		while(res.next()){
			Object clone = hisConstr.newInstance();
			for(int i=0;i<vField.size();i++){
				DynamicInsert(clone,res,vField.get(i),i+1);
			}
			tab.add((BDObject) clone);
		}
		res.close();
		stmt.close();

		return tab;
	}

	public ArrayList<BDObject> Find(Connection conn, String nomcolonne, Object condition) throws Exception{
		if(conn.isClosed()){
			conn = Connex.OracleConnect();
		}

		ArrayList<BDObject> tab = new ArrayList<BDObject>();

		Class<?> thisclass = Class.forName(this.getClass().getName());
		Constructor<?> hisConstr = thisclass.getConstructor();
		ArrayList<Field> vField = new ArrayList<Field>();
		for(Field f : thisclass.getDeclaredFields()){
			if(f.getModifiers() == 0){
				vField.add(f);
			}
		}
		Statement stmt = conn.createStatement();
		String query = "SELECT * FROM "+this.getClass().getSimpleName()+" WHERE "+nomcolonne+"='"+condition+"'"+" ORDER BY "+getAttributSimpleName(vField.get(0));
		ResultSet res = stmt.executeQuery(query);
		while(res.next()){
			Object clone = hisConstr.newInstance();
			for(int i=0;i<vField.size();i++){
				DynamicInsert(clone,res,vField.get(i),i+1);
			}
			tab.add((BDObject) clone);
		}
		res.close();
		stmt.close();

		return tab;
	}

	public ArrayList<BDObject> FindOffsetLimit(Connection conn, String nomcolonne, Object condition, int offset, int limit) throws Exception{
		if(conn.isClosed()){
			conn = Connex.OracleConnect();
		}

		ArrayList<BDObject> tab = new ArrayList<BDObject>();

		Class<?> thisclass = Class.forName(this.getClass().getName());
		Constructor<?> hisConstr = thisclass.getConstructor();
		ArrayList<Field> vField = new ArrayList<Field>();
		for(Field f : thisclass.getDeclaredFields()){
			if(f.getModifiers() == 0){
				vField.add(f);
			}
		}
		Statement stmt = conn.createStatement();
		String query = "SELECT * FROM "+this.getClass().getSimpleName()+" WHERE "+nomcolonne+"='"+condition+"'"+" ORDER BY "+getAttributSimpleName(vField.get(0))+" OFFSET "+offset+" LIMIT "+limit;
		System.out.println("Sql >>> "+query);

		ResultSet res = stmt.executeQuery(query);
		while(res.next()){
			Object clone = hisConstr.newInstance();
			for(int i=0;i<vField.size();i++){
				DynamicInsert(clone,res,vField.get(i),i+1);
			}
			tab.add((BDObject) clone);
		}
		res.close();
		stmt.close();

		return tab;
	}

//----------------------------------------------------------------------------------------
//----------------------------------  FONCTION DELETE ------------------------------------

	public void DeleteFrom(Connection conn,String colonne,String valeur) throws Exception{
		// delete from histosal where id='1';
		if(conn.isClosed()){
			conn = Connex.OracleConnect();
		}

		String query = "DELETE FROM "+this.getClass().getSimpleName()+" WHERE "+colonne+"='"+valeur+"'";
		
		try(Statement stmt = conn.createStatement()){
			int count = stmt.executeUpdate(query);
			System.out.println("Nombre de ligne effaces = "+count);
		}catch(Exception e){
			throw new Exception(">>>>> Erreur lors de l'effacement !!! (si ligne de transaction: executer rollback)");
		}
	}

	public void DeleteFrom(Connection conn,ArrayList<String> colonnes,ArrayList<String> valeurs,String methode) throws Exception{  // methode : et/ou
		// delete from histosal where sal='2500' and job='HOHO';
		if(conn.isClosed()){
			conn = Connex.OracleConnect();
		}
		String condition = "";
		int taille = colonnes.size();
		for(int i=0;i<taille;i++){
			if(i != taille-1){
				condition = condition + colonnes.get(i) + "='" + valeurs.get(i) + "' " + methode + " ";
			}else{
				condition = condition + colonnes.get(i) + "='" + valeurs.get(i) + "'";
			}
		}

		String query = "DELETE FROM "+this.getClass().getSimpleName()+" WHERE "+condition;
		
		try(Statement stmt = conn.createStatement()){
			int count = stmt.executeUpdate(query);
			System.out.println("Nombre de ligne effaces = "+count);
		}catch(Exception e){
			throw new Exception(">>>>> Erreur lors de l'effacement !!! (si ligne de transaction: executer rollback)");
		}
	}

	public void DeleteFrom(Connection conn,Map<Object,Object> where,String methode) throws Exception{  // methode : et/ou
		// delete from histosal where sal='2500' and job='HOHO';
		if(conn.isClosed()){
			conn = Connex.OracleConnect();
		}
		String condition = "";
		Set<Object> whereset = where.keySet();
		int it = 0;
		for(Object o : whereset){
			if(it != whereset.size()-1){
				condition = condition + o + "='" + where.get(o) + "' " + methode + " ";
			}else{
				condition = condition + o + "='" + where.get(o) + "'";
			}
			it++;
		}

		String query = "DELETE FROM "+this.getClass().getSimpleName()+" WHERE "+condition;
		
		try(Statement stmt = conn.createStatement()){
			int count = stmt.executeUpdate(query);
			System.out.println("Nombre de ligne effaces = "+count);
		}catch(Exception e){
			throw new Exception(">>>>> Erreur lors de l'effacement !!! (si ligne de transaction: executer rollback)");
		}
	}

	public void DeleteFrom(Connection conn,Map<Object,List<Object>> where,ArrayList<String> methode) throws Exception{  // methode : et ou ou , et et ou
		// delete from histosal where sal='2500' and job='HOHO';
		// delete from histosal where id='5' or id='4';
		if(conn.isClosed()){
			conn = Connex.OracleConnect();
		}
		String condition = "";
		Set<Object> whereset = where.keySet();
		int it = 0;
		for(Object o : whereset){
			if(it != methode.size()-1){
				List<Object> temp = where.get(o);
				for(Object o2 : temp){
					condition = condition + o + "='" + o2 + "'" + " " + methode.get(it) + " ";
					it++;
				}
			}else{
				int it11 = 0;
				List<Object> temp = where.get(o);
				for(Object o2 : temp){
					if(it11 != temp.size()-1){
						condition = condition + o + "='" + o2 + "'" + " " + methode.get(it) + " ";
					}else{
						condition = condition + o + "='" + o2 + "'" + " ";
					}
					it++;
					it11++;
				}
			}
		}

		String query = "DELETE FROM "+this.getClass().getSimpleName()+" WHERE "+condition;
		
		try(Statement stmt = conn.createStatement()){
			int count = stmt.executeUpdate(query);
			System.out.println("Nombre de ligne effaces = "+count);
		}catch(Exception e){
			throw new Exception(">>>>> Erreur lors de l'effacement !!! (si ligne de transaction: executer rollback)");
		}
	}

// ---------------------------------------------------------------------------------------
// ----------------------------------- FONCTION UPDATE -----------------------------------
	public void UpdateFrom(Connection conn,String setCol,String setVal,String whereCol,String whereVal) throws Exception{
		// update histosal set prix = 1000 where id='1';
		if(conn.isClosed()){
			conn = Connex.OracleConnect();
		}

		boolean isdouble = true;
		try{
			Double.parseDouble(setVal);
		}catch(Exception e){
			isdouble = false;
		}

		if(isdouble){
			setVal = this.pointToVirg(setVal);
		}

		String query = "UPDATE "+this.getClass().getSimpleName()+" SET "+setCol+" = '"+setVal+"' WHERE "+whereCol+" = '"+whereVal+"'";
		System.out.println(query);
		
		try(Statement stmt = conn.createStatement()){
			int count = stmt.executeUpdate(query);
			System.out.println("Nombre de ligne misesa jour = "+count);
		}catch(Exception e){
			throw new Exception(">>>>> Erreur d'update !!! (si ligne de transaction: executer rollback)");
		}
	}

											// use LinkedHashMap
	public void UpdateFrom(Connection conn,Map<Object,Object> champs,Map<Object,Object> condition) throws Exception{ // condition where unique
		// update emp set sal='4000',hiredate='02/11/22' where empno='1';
		if(conn.isClosed()){
			conn = Connex.OracleConnect();
		}

		String modifier = "";
		Set<Object> champset = champs.keySet();
		int it = 0;
		for(Object o : champset){
			if(it != champset.size()-1){
				modifier = modifier + o + "='" + champs.get(o) + "',";
			}else{
				modifier = modifier + o + "='" + champs.get(o) + "'";
			}
			it++;
		}

		String where = "";
		int it2 = 0;
		Set<Object> whereset = condition.keySet();
		for(Object o : whereset){
			if(it2 != whereset.size()-1){
				where = where + o + "='" + condition.get(o) + "'" + " AND ";
			}else{
				where = where + o + "='" + condition.get(o) + "'";
			}
			it2++;
			
		}

		String query = "UPDATE "+this.getClass().getSimpleName()+" SET "+modifier+" WHERE "+where;
		//System.out.println(query);
		
		try(Statement stmt = conn.createStatement()){

			//this.Historiser(conn,"UPDATE");  // HISTORISER D'ABORD ----------------------------------------

			int count = stmt.executeUpdate(query);
			System.out.println("Nombre de ligne affectee = "+count);
		}catch(Exception e){
			throw new Exception(">>>>> Erreur modification !!! (si ligne de transaction: executer rollback)");
		}
	}
										   // use LinkedHashMap      // use LinkedHashMap
	public void UpdateFrom(Connection conn,Map<Object,Object> champs,Map<Object,List<Object>> condition,ArrayList<String> methode) throws Exception{ // methode and/or ou les 2
		// update emp set sal='4000',hiredate='02/11/22' where empno='1';
		if(conn.isClosed()){
			conn = Connex.OracleConnect();
		}

		String modifier = "";
		Set<Object> champset = champs.keySet();
		int it = 0;
		for(Object o : champset){
			if(it != champset.size()-1){
				modifier = modifier + o + "='" + champs.get(o) + "',";
			}else{
				modifier = modifier + o + "='" + champs.get(o) + "'";
			}
			it++;
		}

		String where = "";
		Set<Object> whereset = condition.keySet();
		int it2 = 0;
		for(Object o : whereset){
			if(it2 != methode.size()-1){
				List<Object> temp = condition.get(o);
				for(Object o2 : temp){
					where = where + o + "='" + o2 + "'" + " " + methode.get(it2) + " ";
					it2++;
				}
			}else{
				int it22 = 0;
				List<Object> temp = condition.get(o);
				for(Object o2 : temp){
					if(it22 != temp.size()-1){
						where = where + o + "='" + o2 + "'" + " " + methode.get(it2) + " ";
					}else{
						where = where + o + "='" + o2 + "'" + " ";
					}
					it2++;
					it22++;
				}
			}
		}

		String query = "UPDATE "+this.getClass().getSimpleName()+" SET "+modifier+" WHERE "+where;
		
		try(Statement stmt = conn.createStatement()){
			int count = stmt.executeUpdate(query);
			System.out.println("Nombre de ligne affectee = "+count);
		}catch(Exception e){
			throw new Exception(">>>>> Erreur modification !!! (si ligne de transaction: executer rollback)");
		}
	}

// ---------------------------------------------------------------------------------------
// -------------------------------- FONCTION INSERT --------------------------------------
	public void InsertInto(Connection conn) throws Exception{
		// insert into pratique values('1','Sanda','20','5000');
		if(conn.isClosed()){
			conn = Connex.OracleConnect();
		}
		
		ArrayList<Field> vField = new ArrayList<Field>();
		for(Field f : this.getClass().getDeclaredFields()){
			if(f.getModifiers() == 0){
				vField.add(f);
			}
		}
		
		String values = "";
		int it = 0;
		for(Field f : vField){
			if(it != vField.size()-1){
				Method tempmeth = this.getClass().getMethod("get"+getAttributSimpleName(f));
				String temprep = tempmeth.invoke(this) + "";
				if(getFieldTypeRealName(f).contains("Double")){
					temprep = pointToVirg(temprep);
					System.out.println("Double >>> "+temprep);
				}

				if(getFieldTypeRealName(f).contains("String")){
					if(temprep.contains("'")){
						temprep = escapeSingleQuote(temprep);
					}
				}

				//System.out.println("SQL DATE >> "+f.getType());
				String s = f.getType()+"";
				if(s.contains("java.sql.Date")){
					temprep = OracleDateFormat(temprep);
				}

				if(temprep.contains("null")){
					throw new Exception("La valeur de l'attribut '"+f.getName()+"' de la classe "+this.getClass().getSimpleName()+" est null");
				} else if (temprep.contains("default")) {
					System.out.println("DEFAULT VALUES");
					values = values + "default" + ",";
				} else{
					values = values + "'" + temprep + "',";
				}
			}else{
				Method tempmeth = this.getClass().getMethod("get"+getAttributSimpleName(f));
				String temprep = tempmeth.invoke(this) + "";
				if(getFieldTypeRealName(f).contains("Double")){
					temprep = pointToVirg(temprep);
					System.out.println("Double >>> "+temprep);
				}

				if(getFieldTypeRealName(f).contains("String")){
					if(temprep.contains("'")){
						temprep = escapeSingleQuote(temprep);
					}
				}

				if(getAttributSimpleName(f).toLowerCase().contains("date") && getFieldTypeRealName(f).toLowerCase().contains("string")){
					System.out.println(getFieldTypeRealName(f)+" "+getAttributSimpleName(f)+" contains Date");
					//temprep = myparseStringDate(temprep);
					//System.out.println(">>> temprep "+temprep);
				}

				if(getFieldTypeRealName(f).toLowerCase().contains("date")){
					System.out.println(getFieldTypeRealName(f)+" "+getAttributSimpleName(f)+" contains Date");
					//temprep = myparseDateDate(temprep);
					//System.out.println(">>> temprep "+temprep);
				}

				
				if(temprep.contains("null")){
					throw new Exception("La valeur de l'attribut '"+f.getName()+"' de la classe "+this.getClass().getSimpleName()+" est null");
				}else{
					values = values + "'" + temprep + "'";
				}

			}
			it++;
		}
		String query = "INSERT INTO "+this.getClass().getSimpleName()+" VALUES("+values+")";

		System.out.println("Query: "+query);

		try(Statement stmt = conn.createStatement()){
			int count = stmt.executeUpdate(query);
			System.out.println("Nombre de ligne inseree = "+count);
		}catch(Exception e){
			throw new Exception(">>>>> Erreur d'insertion !!! (si ligne de transaction: executer rollback)");
		}
	}

	public void InsertInto(Connection conn,Map<Object,Object> donnees)throws Exception{
		if(conn.isClosed()){
			conn = Connex.OracleConnect();
		}
		ArrayList<Field> vField = new ArrayList<Field>();
		for(Field f : this.getClass().getDeclaredFields()){
			if(f.getModifiers() == 0){
				vField.add(f);
			}
		}

		if(vField.size() != donnees.size()){
			throw new Exception("Une ou plusieurs attributs de la classe "+this.getClass().getSimpleName()+" est null");
		}

		
		Set<Object> valuesset = donnees.keySet();

		String colonnes = "";
		int it = 0;
		for(Object o : valuesset){
			if(it != valuesset.size()-1){
				colonnes = colonnes + o + ",";
			}else{
				colonnes = colonnes + o;
			}
			it++;
		}

		String values = "";
		int it2 = 0;
		for(Object o : valuesset){
			if(it2 != valuesset.size()-1){
				values = values + "'" + donnees.get(o) + "'" + ",";
			}else{
				values = values + "'" + donnees.get(o) + "'";
			}
			it2++;
		}
		String query = "INSERT INTO "+this.getClass().getSimpleName()+" ("+colonnes+") VALUES("+values+")";
		
		//System.out.println("query : "+query);

		try(Statement stmt = conn.createStatement()){
			int count = stmt.executeUpdate(query);
			System.out.println("Nombre de ligne inseree = "+count);
		}catch(Exception e){
			throw new Exception(">>>>> Erreur d'insertion !!! (si ligne de transaction: executer rollback)");
		}
	}

// ---------------------------------------------------------------------------------------

	public static String getFieldTypeRealName(Field f){
		String avant = f.getType().getSimpleName();
		
		char[] chaine = avant.toCharArray();
		chaine[0] = avant.toUpperCase().charAt(0);
		
		String apres = String.valueOf(chaine);

		return apres;
	}

	public static String getAttributSimpleName(Field f){
		String avant = f.getName();
		
		char[] chaine = avant.toCharArray();
		chaine[0] = avant.toUpperCase().charAt(0);
		
		String apres = String.valueOf(chaine);

		return apres;
	}

	public static String pointToVirg(String number){ // dans Oracle '1000.0' != '1000,0' / le premier n'est pas accepte en number(7,2) 
		char[] chaine = number.toCharArray();
		for(int i=0;i<chaine.length;i++){
			if(chaine[i] == '.'){
				chaine[i] = ',';
			}
		}
		String ret = String.valueOf(chaine);
		return ret;
	}

	public static String escapeSingleQuote(String string){
		char[] chaine = string.toCharArray();
		int singlequote = 0;
		for(char c : chaine){
			if(c == '\''){
				singlequote++;
			}
		}
		char[] newchaine = new char[chaine.length+singlequote];
		int it = 0;
		for (char c : chaine) {
			if (c == '\'') {
				newchaine[it] = c;
				newchaine[it + 1] = '\'';
				it += 2;
			} else {
				newchaine[it] = c;
				it++;
			}
		}

		if(singlequote == 0){
			return string;
		}else{
			return String.valueOf(newchaine);
		}
	}

	public static String myparseStringDate(String s){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String retour = "";
		try{
			retour = sdf.format(sdf.parse(s));
		}catch(Exception e){
			e.printStackTrace();
		}

		return retour;
	}

	public static String myparseDateDate(String s){
		System.out.println("vo tonga === "+s);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		String finale = "";
		try{
			finale = sdf.format(sdf.parse(s));
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Finale : "+finale);

		return "TO_DATE('"+finale+"','DD-MM-YYYY HH24:MI:SS')";
	}

	public static String myParsingSqlDate(String sqlDate){
		return "TO_DATE('"+sqlDate+"','DD-MM-YYYY')";
	}

	public static void DynamicInsert(Object sujet,ResultSet res,Field f,int colonne) throws Exception{
		Method sujetmeth = sujet.getClass().getMethod("set"+getAttributSimpleName(f),f.getType()); // Pour les setters de la class identifiee par l'objet sujet
		Method resmeth = res.getClass().getMethod("get"+getFieldTypeRealName(f),int.class); // Pour les getInt(num col),getDouble(2),getString(3),... de Java 

		resmeth.setAccessible(true);
		sujetmeth.invoke(sujet,resmeth.invoke(res,colonne)); // Emp emp; emp.setEmpno(res.getInt(1));
	}

	public static Object DynamicInsert2(ResultSet res,Field f,int colonne) throws Exception{	// Pour Historiser
		Method resmeth = res.getClass().getMethod("get"+getFieldTypeRealName(f),int.class); // Pour les getInt(num col),getDouble(2),getString(3),... de Java 

		resmeth.setAccessible(true);
		Object temp = resmeth.invoke(res,colonne);
		Object retour = f.getName()+":"+temp+";";

		return retour;
	}

	public static java.sql.Date parseToSqlDate(String sdate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try{
			date = sdf.parse(sdate);
		}catch(Exception e){
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		return sqlDate;
	}

	public static String OracleDateFormat(String sdate){
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yy");

		java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
		java.time.LocalDate ldt = java.time.LocalDate.parse(sdate,dtf);

		java.sql.Date sqlDate = java.sql.Date.valueOf(ldt);

		return sdf2.format(sqlDate);
	}

	public static int compteLigne(Connection cnx, String nomtable, String colonne, String value) throws SQLException {
		int row = 0;

		boolean closed = false;
		if(cnx.isClosed()){
			cnx = Connex.PsqlConnect();
			closed = true;
		}

		String sql = "SELECT count(*) FROM "+nomtable+" WHERE "+colonne+"='"+value+"'";
		System.out.println("Sql >>> "+sql);
		Statement stmt = cnx.createStatement();
		ResultSet res = stmt.executeQuery(sql);
		while(res.next()){
			row = res.getInt(1);
		}

		if(closed){
			cnx.close();
		}
		return row;
	}

	public List<BDObject> recherchePlanV1(Connection cnx,String colonne1, String valeur1, String colonne2, String valeur2, String intitule) throws Exception {
		List<BDObject> resultats = new ArrayList<>();

		boolean closed = false;
		if(cnx.isClosed()){
			cnx = Connex.PsqlConnect();
			closed = true;
		}

		Class<?> thisclass = Class.forName(this.getClass().getName());
		Constructor<?> hisConstr = thisclass.getConstructor();
		List<Field> vField = new ArrayList<>();
		for(Field f : thisclass.getDeclaredFields()){
			if(f.getModifiers() == 0){
				vField.add(f);
			}
		}

		String sql = "SELECT * FROM "+this.getClass().getSimpleName()+" WHERE "+colonne1+"='"+valeur1+"' AND ("+colonne2+" LIKE '"+valeur2+"' OR LOWER(intitule) LIKE '"+intitule+"')";
		System.out.println("Sql >>> "+sql);
		Statement stmt = cnx.createStatement();
		ResultSet res = stmt.executeQuery(sql);

		while(res.next()){
			Object clone = hisConstr.newInstance();
			for(int i=0;i<vField.size();i++){
				DynamicInsert(clone,res,vField.get(i),i+1);
			}
			resultats.add((BDObject) clone);
		}

		if(closed){
			cnx.close();
		}

		return resultats;
	}
}