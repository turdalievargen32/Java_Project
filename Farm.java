package Java_Project;

public class Farm {
    private Cell[][] grid;
    private int width;
    private int height;

    public Farm(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Cell();
            }
        }
        // Инициализация начальных состояний
        init();
    }

    private void init() {
        // Посадить урожай в случайных местах
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (Math.random() < 0.2) {  // 20% шанс наличия урожая
                    grid[i][j].plantCrop();
                }
            }
        }
        // Расставить фермеров
        grid[10][10].setFarmer(new Farmer());
        // Расставить вредителей
        grid[15][15].setPest(new Pest());
        // Расставить защитников
        grid[20][20].setProtector(new Protector());
    }

    public void step() {
        // Создаем копию текущего состояния для обработки изменений без конфликтов
        Cell[][] newGrid = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newGrid[i][j] = new Cell();
                if (grid[i][j].hasCrop()) {
                    newGrid[i][j].plantCrop();
                }
            }
        }

        // Обработка действий и перемещений фермеров
        processFarmers(newGrid);
        processPests(newGrid);
        processProtectors(newGrid);

        // Обновляем основную сетку
        grid = newGrid;
    }

    private void processFarmers(Cell[][] newGrid) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j].getFarmer() != null) {
                    // Пример случайного перемещения фермера
                    int[] newLocation = getRandomLocation(i, j);
                    newGrid[newLocation[0]][newLocation[1]].setFarmer(grid[i][j].getFarmer());
                    newGrid[newLocation[0]][newLocation[1]].getFarmer().plant(newGrid[newLocation[0]][newLocation[1]]);
                }
            }
        }
    }

    private void processPests(Cell[][] newGrid) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j].getPest() != null) {
                    int[] newLocation = getRandomLocation(i, j);
                    newGrid[newLocation[0]][newLocation[1]].setPest(grid[i][j].getPest());
                    newGrid[newLocation[0]][newLocation[1]].getPest().attack(newGrid[newLocation[0]][newLocation[1]]);
                }
            }
        }
    }

    private void processProtectors(Cell[][] newGrid) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j].getProtector() != null) {
                    int[] newLocation = getRandomLocation(i, j);
                    newGrid[newLocation[0]][newLocation[1]].setProtector(grid[i][j].getProtector());
                    grid[i][j].getProtector().protect(newGrid, newLocation[0], newLocation[1]);
                }
            }
        }
    }

    private int[] getRandomLocation(int x, int y) {
        int newX = (x + (int)(Math.random() * 3) - 1 + width) % width;
        int newY = (y + (int)(Math.random() * 3) - 1 + height) % height;
        return new int[]{newX, newY};
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
