/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.conspref.consprefboweb.util.filter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

public class TestFilter {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String filterParam = new String(Files.readAllBytes(Paths.get("XYZ")), "UTF-8");
		
		
		Filter filter = new Filter(filterParam);
		
		

		
		Clause clause = filter.getClause("scarpe");

		Literal literal = clause.getLiteral(Operator.GT);

		

		
		Optional<Object> scarpeGt = filter.get("scarpe", Operator.GT);

		
		Optional<String> scarpeLt = filter.getString("scarpe", Operator.IN);

		scarpeLt.ifPresent(v -> System.out.println("scarpeLt.get(): " +v));

	}

}
