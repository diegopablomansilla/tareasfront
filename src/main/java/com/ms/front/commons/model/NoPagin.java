package com.ms.front.commons.model;

import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue.ValueType;

public class NoPagin extends ObjectModel {

	private Integer cantRows;

	private Integer thisPageColumns;
	private Object[][] thisPageItems;

	public Integer getCantRows() {
		return cantRows;
	}

	public void setCantRows(Integer cantRows) {
		this.cantRows = cantRows;
	}

	public Integer getThisPageColumns() {
		return thisPageColumns;
	}

	public void setThisPageColumns(Integer thisPageColumns) {
		this.thisPageColumns = thisPageColumns;
	}

	public Object[][] getThisPageItems() {
		return thisPageItems;
	}

	public void setThisPageItems(Object[][] thisPageItems) {
		this.thisPageItems = thisPageItems;
	}

	public String toString() {

		String s = "";

		s += "\n" + "ROWS=[" + cantRows + "] ";

		if (thisPageItems != null) {
			s += "\n\nTHIS_PAGE_COLUMNS=[" + thisPageColumns + "]";

			for (Object[] row : thisPageItems) {
				s += "\n" + Arrays.toString(row);
			}
		}

		return s;
	}

	public JsonObject toJson() {

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("cantRows", this.getCantRows());

		j.set("thisPageColumns", this.getThisPageColumns());
		j.set("thisPageColumns", this.getThisPageColumns());

		j.set("thisPageItems", thisPageItems);

		return j.build();
	}

	public static NoPagin fromJson(String json) {
		if (json == null || json.trim().length() == 0) {
			return new NoPagin();
		}

		JsonObject jo = Json.createReader(new StringReader(json)).readObject();

		return fromJson(jo);
	}

	public static NoPagin fromJson(JsonObject jo) {

		if (jo == null) {
			return null;
		}

		if (jo.isEmpty()) {
			return null;
		}

//		String att = "payload";
//
//		if (jo.containsKey(att) && jo.isNull(att)) {
//			return null;
//		}
//
//		jo = jo.getJsonObject(att);

		NoPagin p = new NoPagin();

		String att = "cantRows";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			p.setCantRows(jo.getInt(att));
		}

		att = "thisPageColumns";
		if (jo.containsKey(att) && !jo.isNull(att)) {
			p.setThisPageColumns(jo.getInt(att));
		}

		att = "thisPageItems";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			JsonArray jsonArrayRows = jo.getJsonArray(att);

			Object[][] table = new Object[jsonArrayRows.size()][p.getThisPageColumns()];

			for (int i = 0; i < jsonArrayRows.size(); i++) {
				JsonArray jsonArrayRow = jsonArrayRows.getJsonArray(i);

				for (int j = 0; j < jsonArrayRow.size(); j++) {

					if (jsonArrayRow.isNull(j)) {

						table[i][j] = null;

					} else { //-----------

						if (jsonArrayRow.get(j).getValueType() == ValueType.STRING) {
							table[i][j] = jsonArrayRow.getString(j);
						} else if (jsonArrayRow.get(j).getValueType() == ValueType.NUMBER) {							
							
							if (jsonArrayRow.getJsonNumber(j).numberValue().getClass() == Integer.class) {
								table[i][j] = jsonArrayRow.getJsonNumber(j).intValue();
							} else if (jsonArrayRow.getJsonNumber(j).numberValue().getClass() == Long.class) {
								table[i][j] = jsonArrayRow.getJsonNumber(j).longValue();
							} else if (jsonArrayRow.getJsonNumber(j).numberValue().getClass() == Double.class) {
								table[i][j] = jsonArrayRow.getJsonNumber(j).doubleValue();
							} else if (jsonArrayRow.getJsonNumber(j).numberValue().getClass() == BigDecimal.class) {
								table[i][j] = jsonArrayRow.getJsonNumber(j).bigDecimalValue();
							} else if (jsonArrayRow.getJsonNumber(j).numberValue().getClass() == BigInteger.class) {
								table[i][j] = jsonArrayRow.getJsonNumber(j).bigIntegerValue();
							}
						} else if (jsonArrayRow.get(j).getValueType() == ValueType.FALSE
								|| jsonArrayRow.get(j).getValueType() == ValueType.TRUE) {
							
							table[i][j] = jsonArrayRow.getBoolean(j);
						}

					} //------------

				}

			}

			p.setThisPageItems(table);
		}

		return p;
	}

}
