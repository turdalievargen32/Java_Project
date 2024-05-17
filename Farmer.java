package Java_Project;

public class Farmer {
    public void plant(Cell cell) {
        if (!cell.hasCrop()) {
            cell.plantCrop();
        }
    }
}

class Pest {
    public void attack(Cell cell) {
        if (cell.hasCrop() && cell.getProtector() == null) {
            cell.removeCrop();
        }
    }
}

class Protector {
    // Защитник патрулирует и убивает вредителей в соседних клетках
    public void protect(Cell[][] grid, int x, int y) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx;
                int ny = y + dy;
                // Проверяем, находится ли соседняя клетка в пределах фермы
                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length) {
                    Cell cell = grid[nx][ny];
                    if (cell.getPest() != null) {
                        // Уничтожаем вредителя в этой клетке
                        cell.setPest(null);
                    }
                }
            }
        }
    }
}

