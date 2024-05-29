package ru.rodyk.histgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MyGdxGame extends ApplicationAdapter implements ApplicationListener{
    Stage stage;
    int[] rightq = new int[5];
    SpriteBatch batch;
    Music hoika;
    Texture[] img;
    String srt = "krggrg";
    Texture chel;
    Vector2 pos;
    Room room_;
    boolean fl = false;
    Camera camera;
    int level = 0;
    TextField okno;
    String texx1, texx2, texx3, texx4;
    TextInputListener okno1, okno2, okno3, okno4, okno5, oknop;
    boolean isWPressed = false, isAPressed = false, isSPressed = false, isDPressed = false;
    ArrayList<ArrayList<String>> questions, answers;
    boolean[][] prog = new boolean[5][4];

    @Override
    public void create() {
        hoika = Gdx.audio.newMusic(Gdx.files.internal("song/luft.mp3"));
        hoika.setLooping(true);
        hoika.play();
        stage = new Stage();
        batch = new SpriteBatch();
        questions = new ArrayList<>();
        answers = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            questions.add(new ArrayList<>());
            answers.add(new ArrayList<>());
            for (int j = 0; j < 4; j++) {
                prog[i][j] = false;
            }
        }
        questions.get(0).add("Дата начала второго сражения под Эль-Аламейном? (ДДММГГГГ)");
        questions.get(0).add("Командующий британскими войсками во время второго сражения под Эль-Аламейном (фамилия)");
        questions.get(0).add("Какая масштабная операция началась на восточном фронте вскоре после сражения под Эль-Аламейном");
        questions.get(0).add("Как называлась группировка немецких войск, участвовавшая, в том числе, в сражении под Эль-Аламейном?");

        questions.get(1).add("Главный координатор мятежа против Второй Испанской Республики");
        questions.get(1).add("Дата начала Гражданской войны в Испании (ДДММГГГГ)");
        questions.get(1).add("Дата начала первого крупного наступления на Мадрид националистами (ДДММГГГГ)");
        questions.get(1).add("Дата начала весеннего наступления националистов в Арагоне (ДДМММГГГГ)");

        questions.get(2).add("Со смертью какого человека началась эра милитаристов в Китае?");
        questions.get(2).add("Кто возглавил Чжилийскую клику в 1919 году?");
        questions.get(2).add("Название операции Национально-революционной армии в 1926-1928 годах");
        questions.get(2).add("Самая могущественная клика Юга в начале эры милитаристов");

        questions.get(3).add("Город, в котором началась красная революция и гражданская война в Финляндии");
        questions.get(3).add("Главнокомандующий белыми войсками во время гражаданской войны в Финляндии (фамилия)");
        questions.get(3).add("Дата начала наступления белых войск на Тампере (ДДММГГГГ)");
        questions.get(3).add("Дата захвата Хельсинки(ов) немецкими войсками (ДДММГГГГ)");

        questions.get(4).add("Какая организация потеряла репутацию в глазах мирового сообщества после второй итало-эфиопскй войны");
        questions.get(4).add("Начало Эфиопского рожденственского наступления");
        questions.get(4).add("Занятие частями Итальянской армии Аддис-Абебы");
        questions.get(4).add("Название созданной Италией колонии, в которую вошла аннексированная Эфиопия");

        answers.get(0).add("23101942");
        answers.get(0).add("монтгомери");
        answers.get(0).add("уран");
        answers.get(0).add("африканскийкорпус");

        answers.get(1).add("эмилиомола");
        answers.get(1).add("17071936");
        answers.get(1).add("15101936");
        answers.get(1).add("09031938");

        answers.get(2).add("юаньшикай");
        answers.get(2).add("цаокунь");
        answers.get(2).add("северныйпоход");
        answers.get(2).add("стараякликагуанси");

        answers.get(3).add("гельсингфорс");
        answers.get(3).add("маннергейм");
        answers.get(3).add("06041918");
        answers.get(3).add("13041918");

        answers.get(4).add("лиганаций");
        answers.get(4).add("14121935");
        answers.get(4).add("05051936");
        answers.get(4).add("итальянскаявосточнаяафрика");
        img = new Texture[90];
        chel = new Texture("Characters/Untitled.png");
        img[0] = new Texture("Tiles/Untitled.png");
        img[1] = new Texture("Tiles/platformPack_tile019.png");
        img[2] = new Texture("Tiles/START.png");
        img[3] = new Texture("Tiles/platformPack_tile021.png");
        img[4] = new Texture("Tiles/platformPack_tile022.png");
        img[61] = new Texture("Tiles/DOWN.png");
        img[62] = new Texture("Tiles/UP.png");
        img[63] = new Texture("Tiles/RIGHT.png");
        img[64] = new Texture("Tiles/LEFT.png");
        img[89] = new Texture("Tiles/RESULTS.png");
        pos = new Vector2(928, 32);
        camera = new OrthographicCamera(560, 315);
        room_ = new Room(480, 1440, 480, -480);
        okno1 = new TextInputListener() {
            @Override
            public void input(String text) {
                texx1 = text;
                if (Objects.equals(texx1, answers.get(level).get(1 - 1))) {
                    rightq[level]++;
                    Gdx.input.getTextInput(oknop, "Верно", "", "Вводить текст не обязательно");
                }
                if (prog[level][0] && prog[level][1] && prog[level][2] && prog[level][3] && level != 4) {
                    pos.x = 928;
                    pos.y = 32;
                    level++;
                }
            }

            @Override
            public void canceled() {
                if (prog[level][0] && prog[level][1] && prog[level][2] && prog[level][3] && level != 4) {
                    pos.x = 928;
                    pos.y = 32;
                    level++;
                }
            }
        };
        okno2 = new TextInputListener() {
            @Override
            public void input(String text) {
                texx2 = text;
                if (Objects.equals(texx2, answers.get(level).get(2 - 1))) {
                    rightq[level]++;
                    Gdx.input.getTextInput(oknop, "Верно", "", "Вводить текст не обязательно");
                }
                if (prog[level][0] && prog[level][1] && prog[level][2] && prog[level][3] && level != 4) {
                    pos.x = 928;
                    pos.y = 32;
                    level++;
                }
            }

            @Override
            public void canceled() {
                if (prog[level][0] && prog[level][1] && prog[level][2] && prog[level][3] && level != 4) {
                    pos.x = 928;
                    pos.y = 32;
                    level++;
                }
            }
        };
        okno3 = new TextInputListener() {
            @Override
            public void input(String text) {
                texx3 = text;
                if (Objects.equals(texx3, answers.get(level).get(3 - 1))) {
                    rightq[level]++;
                    Gdx.input.getTextInput(oknop, "Верно", "", "Вводить текст не обязательно");
                }
                if (prog[level][0] && prog[level][1] && prog[level][2] && prog[level][3] && level != 4) {
                    pos.x = 928;
                    pos.y = 32;
                    level++;
                }
            }

            @Override
            public void canceled() {
                if (prog[level][0] && prog[level][1] && prog[level][2] && prog[level][3] && level != 4) {
                    pos.x = 928;
                    pos.y = 32;
                    level++;
                }
            }
        };
        okno4 = new TextInputListener() {
            @Override
            public void input(String text) {
                texx4 = text;
                if (Objects.equals(texx4, answers.get(level).get(4 - 1))) {
                    rightq[level]++;
                    Gdx.input.getTextInput(oknop, "Верно", "", "Вводить текст не обязательно");
                }
                if (prog[level][0] && prog[level][1] && prog[level][2] && prog[level][3] && level != 4) {
                    pos.x = 928;
                    pos.y = 32;
                    level++;
                }
            }

            @Override
            public void canceled() {
                if (prog[level][0] && prog[level][1] && prog[level][2] && prog[level][3] && level != 4) {
                    pos.x = 928;
                    pos.y = 32;
                    level++;
                }
            }
        };
        okno5 = new TextInputListener() {
            @Override
            public void input(String text) {
                Gdx.app.exit();
            }

            @Override
            public void canceled() {
                Gdx.app.exit();
            }
        };
        oknop = new TextInputListener() {
            @Override
            public void input(String text) {}

            @Override
            public void canceled() {}
        };
        ImageButton buttonW = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Characters/W.png"))));
        ImageButton buttonA = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Characters/A.png"))));
        ImageButton buttonS = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Characters/S.png"))));
        ImageButton buttonD = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Characters/D.png"))));
        buttonW.setSize(200,200);
        buttonA.setSize(200,200);
        buttonS.setSize(200,200);
        buttonD.setSize(200,200);
        buttonW.setPosition(260, 260);
        buttonA.setPosition(40, 40);
        buttonS.setPosition(260, 40);
        buttonD.setPosition(480, 40);
        buttonW.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isWPressed = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isWPressed = false;
            }
        });
        buttonA.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isAPressed = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isAPressed = false;
            }
        });
        buttonS.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isSPressed = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isSPressed = false;
            }
        });
        buttonD.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isDPressed = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isDPressed = false;
            }
        });
        stage.addActor(buttonW);
        stage.addActor(buttonA);
        stage.addActor(buttonS);
        stage.addActor(buttonD);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
//        isWPressed = Gdx.input.isKeyPressed(Input.Keys.W);
//        isDPressed = Gdx.input.isKeyPressed(Input.Keys.D);
//        isAPressed = Gdx.input.isKeyPressed(Input.Keys.A);
//        isSPressed = Gdx.input.isKeyPressed(Input.Keys.S);

        stage.act();

        if (isDPressed && isWPressed && isAPressed && isSPressed) {
            pos.x++;
            pos.x--;
        } else {
            if (isDPressed) {
                if (isWPressed) {
                    if (isSPressed) {
                        pos.x += 4;
                    } else {
                        if (pos.x + 64 < room_.x2 && pos.y < room_.y1) {
                            pos.x += 2;
                            pos.y += 2;
                        }
                    }
                } else if (isSPressed) {
                    if (pos.x + 64 < room_.x2 && pos.y - 64 > room_.y2) {
                        pos.x += 2;
                        pos.y -= 2;
                    }
                } else if (isAPressed) {
                    pos.x++;
                    pos.x--;
                } else {
                    if (pos.x + 64 < room_.x2) {
                        pos.x += 2;
                        if (pos.x + 64 < room_.x2) {
                            pos.x += 2;
                        }
                    }
                }
            }
            if (isAPressed) {
                if (isWPressed) {
                    if (isSPressed) {
                        pos.x -= 4;
                    } else {
                        if (pos.x > room_.x1 && pos.y < room_.y1) {
                            pos.x -= 2;
                            pos.y += 2;
                        }
                    }
                } else if (isSPressed) {
                    if (pos.x > room_.x1 && pos.y - 64 > room_.y2) {
                        pos.x -= 2;
                        pos.y -= 2;
                    }
                } else if (isDPressed) {
                    pos.x++;
                    pos.x--;
                } else {
                    if (pos.x > room_.x1) {
                        pos.x -= 2;
                        if (pos.x > room_.x1) {
                            pos.x -= 2;
                        }
                    }
                }
            }
            if (isWPressed) {
                if (!isAPressed && !isDPressed && !isSPressed){
                    if (pos.y < room_.y1) {
                        pos.y += 2;
                        if (pos.y < room_.y1) {
                            pos.y += 2;
                        }
                    }
                }
            }
            if (isSPressed) {
                if (!isAPressed && !isDPressed && !isWPressed){
                    if (pos.y - 64 > room_.y2) {
                        pos.y -= 2;
                        if (pos.y - 64 > room_.y2) {
                            pos.y -= 2;
                        }
                    }
                }
            }
        }

        if(pos.x == 1440 - 64 && pos.y == 480) {
            if(!prog[level][1 - 1]) {
                prog[level][1 - 1] = true;
                Gdx.input.getTextInput(okno1, questions.get(level).get(1 - 1), "", "Введите ответ без пробелов строчными буквами");
            }
        }
        if(pos.x == 1440 - 64 && pos.y == -416) {
            if(!prog[level][2 - 1]) {
                prog[level][2 - 1] = true;
                Gdx.input.getTextInput(okno2, questions.get(level).get(2 - 1), "", "Введите ответ без пробелов строчными буквами");
            }
        }
        if(pos.x == 480 && pos.y == 480) {
            if(!prog[level][3 - 1]) {
                prog[level][3 - 1] = true;
                Gdx.input.getTextInput(okno3, questions.get(level).get(3 - 1), "", "Введите ответ без пробелов строчными буквами");
            }
        }
        if(pos.x == 480 && pos.y == -416) {
            if(!prog[level][4 - 1]) {
                prog[level][4 - 1] = true;
                Gdx.input.getTextInput(okno4, questions.get(level).get(4 - 1), "", "Введите ответ без пробелов строчными буквами");
            }
        }

        if(prog[4][0] && prog[4][1] && prog[4][2] && prog[4][3] && !fl && pos.x == 1440 - 64 && pos.y == 32) {
            fl = true;
            Gdx.input.getTextInput(okno5, "Игра окончена, ваши результаты:" + " Второе сражение при Эль-Аламейне: " + rightq[0] + "  Гражданская война в Испании: " + rightq[1] + "  Эра милитаристов в Китае: " + rightq[2] + "  Гражданская война в Финляндии: " + rightq[3] + "  Вторая итало-эфиопская война: " + rightq[4], "", "Нажмите ОК");
        }

        camera.position.set(pos.x + 32, pos.y + 32, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                batch.draw(img[room_.room.get(i).get(j)], room_.x1 + j * 64, room_.y1 - i * 64);
            }
        }
        batch.draw(chel, pos.x, pos.y);
        batch.end();

        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        chel.dispose();
    }
}
