package geometries;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ShapePoint extends GeoPoint{

	private static final long serialVersionUID = 1L;
	// ex.: "shape_id","shape_pt_lat","shape_pt_lon","shape_pt_sequence","shape_dist_traveled"
	// ex.: shape: 3217,-25.4757686477818,-49.2923877163312,3281146,24.441
	private String id;
	private String pointSequence;
	private String distanceTraveled;
	private String route;
	private List<String> listStopTimestamp;

	public ShapePoint(String id, String latitude, String longitude, String pointSequence, String distanceTraveled) {
		super(latitude, longitude);
		this.id = id;
		this.pointSequence = pointSequence;
		this.distanceTraveled = distanceTraveled;
		this.listStopTimestamp = new ArrayList<String>();
	}

	public ShapePoint(String route, String id, String latitude, String longitude, String pointSequence,
			String distanceTraveled) {
		this(id, latitude, longitude, pointSequence, distanceTraveled);
		this.route = route;
	}

	public static ShapePoint createShapePoint(String line) {
		StringTokenizer st = new StringTokenizer(line, ",");
		return new ShapePoint(st.nextToken().replace("\"", ""), 
				st.nextToken().replace("\"", ""),
				st.nextToken().replace("\"", ""), 
				st.nextToken().replace("\"", ""), 
				st.nextToken().replace("\"", ""));
	}

	public static ShapePoint createShapePointRoute(String line) {
		StringTokenizer st = new StringTokenizer(line, ",");
		return new ShapePoint(st.nextToken().replace("\"", ""), 
				st.nextToken().replace("\"", ""),
				st.nextToken().replace("\"", ""), 
				st.nextToken().replace("\"", ""), 
				st.nextToken().replace("\"", ""),
				st.nextToken().replace("\"", ""));
	}
	
	public static ShapePoint createShapePointRoute(String line, Integer[] sequence, String separatorCharacter) {
        String[] splittedLine = line.split(separatorCharacter);
    
        return new ShapePoint(splittedLine[sequence[0]].replace("\"", ""), 
                splittedLine[sequence[1]].replace("\"", ""),
                splittedLine[sequence[2]].replace("\"", ""),
                splittedLine[sequence[3]].replace("\"", ""),
                splittedLine[sequence[4]].replace("\"", ""),
                splittedLine[sequence[5]].replace("\"", ""));
    }
	
//	public static ShapePoint createShapePointRoute(GenericObject line) {
//		return new ShapePoint(line.getData().get("route_id").toString(),
//				line.getData().get("shape_id").toString(),
//				line.getData().get("shape_pt_lat").toString(),
//				line.getData().get("shape_pt_lon").toString(),
//				line.getData().get("shape_pt_sequence").toString(),
//				line.getData().get("shape_dist_traveled").toString());
//	}
//	
	public void addTimestampStop(String timestamp) {
		this.listStopTimestamp.add(timestamp);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPointSequence() {
		return pointSequence;
	}

	public void setPointSequence(String pointSequence) {
		this.pointSequence = pointSequence;
	}

	public Float getDistanceTraveled() {
		return Float.valueOf(distanceTraveled);
	}

	public void setDistanceTraveled(String distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public List<String> getListStopTimestamp() {
		return listStopTimestamp;
	}

	public void setListStopTimestamp(List<String> listStopTimestamp) {
		this.listStopTimestamp = listStopTimestamp;
	}

	@Override
	public String toString() {
		return "ShapePoint [id=" + id + ", pointSequence=" + pointSequence + ", distanceTraveled=" + distanceTraveled
				+ ", route=" + route + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((distanceTraveled == null) ? 0 : distanceTraveled.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pointSequence == null) ? 0 : pointSequence.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShapePoint other = (ShapePoint) obj;
		if (distanceTraveled == null) {
			if (other.distanceTraveled != null)
				return false;
		} else if (!distanceTraveled.equals(other.distanceTraveled))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pointSequence == null) {
			if (other.pointSequence != null)
				return false;
		} else if (!pointSequence.equals(other.pointSequence))
			return false;
		return true;
	}

	@Override
	public int compareTo(GeoPoint otherGeoPoint) {
		ShapePoint otherShape = (ShapePoint) otherGeoPoint;
		if (Integer.parseInt(this.pointSequence) < Integer.parseInt(otherShape.pointSequence)) {
            return -1;
        }
        if (Integer.parseInt(this.pointSequence) > Integer.parseInt(otherShape.pointSequence)) {
            return 1;
        }
        return 0;
	}
}