/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busguide;

import java.util.Objects;

/**
 *
 * @author elvis
 */
public class Vertex {

	private final String busStopId;

	public Vertex(String busStopCode) {
		busStopId = busStopCode;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 61 * hash + Objects.hashCode(this.busStopId);
		return hash;
	}

	public String getBusStopId() {
		return busStopId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Vertex other = (Vertex) obj;
		if (!Objects.equals(this.busStopId, other.busStopId)) {
			return false;
		}
		return true;
	}

}
