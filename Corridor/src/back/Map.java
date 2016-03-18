package back;

import java.awt.Point;
import java.util.ArrayList;
import org.json.*;

/**
 *
 * @author Szlatyka
 */
public class Map {

    private final String FILECONTENTTYPE = "text/corrydormap";
    private TileType[][] tiles;

    public Map(String file) {
        String tmp = FileProvider.loadStringFromFile(file);
        if (tmp != null) {
            JSONObject obj = new JSONObject(tmp);
            if (obj.getString("contentType").equals(FILECONTENTTYPE)) {
                this.tiles = new TileType[19][19];
                JSONArray columns = obj.getJSONArray("mapTiles");
                for (int y = 0; y < columns.length(); y++) {
                    JSONArray row = columns.getJSONArray(y);
                    for (int x = 0; x < row.length(); x++) {
                        String tile = row.get(x).toString();
                        if (tile.matches("^-?\\d+$")) {
                            int numeric = Integer.parseInt(tile);
                            switch (numeric) {
                                case 0:
                                    this.tiles[y][x] = TileType.EMTPYWALL;
                                    break;
                                case 1:
                                    this.tiles[y][x] = TileType.NORMAL;
                                    break;
                                case 2:
                                    this.tiles[y][x] = TileType.HEDGE;
                                    break;
                                case 3:
                                    this.tiles[y][x] = TileType.WALL;
                                    break;
                                case 4:
                                    this.tiles[y][x] = TileType.PORTAL;
                                    break;
                                case 5:
                                    this.tiles[y][x] = TileType.MOUNTAIN;
                                    break;
                                case 8:
                                    this.tiles[y][x] = TileType.MIDDLE;
                                    break;
                                default:
                                    this.tiles[y][x] = TileType.NORMAL;
                                    break;
                            }
                        } else {
                            this.tiles[y][x] = TileType.EMTPYWALL;
                        }
                    }
                }
            }
        }
    }

    public Boolean setWall(Point p, TileType type) {
        return this.setWall(p.x, p.y, type);
    }

    public Boolean setWall(int x, int y, TileType type) {
        if (this.tiles[y][x] != TileType.WALL) {
            this.tiles[y][x] = type;
            return true;
        } else {
            return false;
        }
    }

    public TileType getTile(Point p) {
        return this.getTile(p.x, p.y);
    }

    public TileType getTile(int x, int y) {
        return this.tiles[y][x];
    }

    public TileType[][] getTiles() {
        return this.tiles;
    }

    public TileType getWall(Point p) {
        return this.getTile(p);
    }

    public TileType getWall(int x, int y) {
        return this.getTile(x, y);
    }

    public ArrayList<Point> getAvailableTiles(Point position, int moves) {
        ArrayList<Point> avtiles = getAvailableTiles(position, moves, new ArrayList<Point>());
        avtiles.remove(position);
        return avtiles;
    }

    private ArrayList<Point> getAvailableTiles(Point pos, int moves, ArrayList<Point> points) {
        if (moves > 0) {
            int x = pos.x;
            int y = pos.y;
            //Felfelé út vizsgálata
            if (y > 0 && getWall(x, y - 1) == TileType.EMTPYWALL && getTile(x, y - 2) != TileType.MOUNTAIN) {
                Point tmp = new Point(x, y - 2);
                if (!points.contains(tmp)) {
                    points.add(tmp);
                }
                points = getAvailableTiles(tmp, moves - 1, points);
            }
            //Lefelé út vizsgálata
            if (y < tiles[0].length - 1 && getWall(x, y + 1) == TileType.EMTPYWALL && getTile(x, y + 2) != TileType.MOUNTAIN) {
                Point tmp = new Point(x, y + 2);
                if (!points.contains(tmp)) {
                    points.add(tmp);
                }
                points = getAvailableTiles(tmp, moves - 1, points);
            }
            //Balrafelé út vizsgálata
            if (x > 0 && getWall(x - 1, y) == TileType.EMTPYWALL && getTile(x - 2, y) != TileType.MOUNTAIN) {
                Point tmp = new Point(x - 2, y);
                if (!points.contains(tmp)) {
                    points.add(tmp);
                }
                points = getAvailableTiles(tmp, moves - 1, points);
            }
            //Jobbrafelé út vizsgálata
            if (x < tiles.length - 1 && getWall(x + 1, y) == TileType.EMTPYWALL && getTile(x + 2, y) != TileType.MOUNTAIN) {
                Point tmp = new Point(x + 2, y);
                if (!points.contains(tmp)) {
                    points.add(tmp);
                }
                points = getAvailableTiles(tmp, moves - 1, points);
            }
        }
        return points;
    }

    public ArrayList<Point> getAvailableWalls() {
        ArrayList<Point> result = new ArrayList<>();
        for (int y = 0; y < this.tiles.length; y++) {
            for (int x = 0; x < this.tiles[y].length; x++) {
                if (this.getWall(x, y) == TileType.EMTPYWALL) {
                    result.add(new Point(x, y));
                }
            }
        }
        return result;
    }

    public ArrayList<Point> getAvailableHedges(Point position) {
        ArrayList<Point> result = new ArrayList<>();
        int x = position.x;
        int y = position.y;
        if (x > 0 && getTile(x - 1, y) == TileType.HEDGE) {
            result.add(new Point(x - 1, y));
        }
        if (x < tiles.length - 1 && getTile(x + 1, y) == TileType.HEDGE) {
            result.add(new Point(x + 1, y));
        }
        if (y > 0 && getTile(x, y - 1) == TileType.HEDGE) {
            result.add(new Point(x, y - 1));
        }
        if (y < tiles.length - 1 && getTile(x, y + 1) == TileType.HEDGE) {
            result.add(new Point(x, y + 1));
        }
        return result;
    }
}
