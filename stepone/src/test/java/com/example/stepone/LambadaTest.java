package com.example.stepone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.sl.draw.geom.GuideIf;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class LambadaTest {

    @Test
    public void one() {
        System.out.println("測試");
        new Thread(() -> System.out.println("現成執行")).start();
    }

    @Test
    public void two() {
        String str = Doing.convert("123",  new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s + "草寺寧的嘛";
            }
        });
        // Doing.convert("123", (String s) -> { return Long.valueOf(s); });
        // Doing.convert("123", s -> Long.valueOf(s));
        Long a = Doing.convert("123", Long::valueOf);
        Long b = Long.valueOf("123");
        System.out.println(Objects.equals(a, b));
    }

    @Test
    public void three() {
        List<Person> ps = persons();
        ps.stream().distinct().filter(Person::less18).forEach(System.out::println);
        // 普通列表
        int[] ins = { 1, 2, 3 };
        Arrays.stream(ins).distinct().forEach(System.out::println);
        // MAP
        personMap().forEach((key, value) -> System.out.println(value));
    }

    @Test
    public void fourMap() {
        // map 就是 用來 轉換的
        // 這裡是 Person 轉 PersonView
        persons().stream().distinct().map(PersonView::init).limit(3).forEach(System.out::println);
        // Person 提取 年齡，轉為 int，過濾 < 18，然後打印出來。
        persons().stream().map(Person::getAge).distinct().filter(age->age < 18).sorted(
                // Comparator.comparingInt(a -> a)
                (a1, a2) -> a2 - a1 // 倒敘
        ).limit(2).forEach(System.out::println);
    }

    @Test
    public void fiveMapFlat() {
        // skip 是 跳過前幾個
        // persons().stream().distinct().skip(2).limit(1).forEach(System.out::println);

        // flatmap 是將 List 裡面的 List 拿出來，合併為一個 流
        persons().stream().distinct().flatMap(p-> p.getHobbies().stream()).forEach(System.out::println);

        System.out.println("--- 去重複順序 不一樣的 不一樣的 結果 ---");
        persons().stream().flatMap(p-> p.getHobbies().stream()).distinct().forEach(System.out::println);
    }

    @Test
    public void sixEnd() {
        // forEach
        // count 得到數量
        // System.out.println(persons().stream().count());
        // min 最小值
        System.out.println(persons().stream().min((p1, p2) -> p1.getAge() - p2.getAge()));
        // max 最大值
        System.out.println(persons().stream().max((p1, p2) -> p1.getAge() - p2.getAge()));

        // collect 轉換為 LIST 集合
        List<String> names = persons().stream().map(Person::getName).distinct().collect(Collectors.toList());
        names.stream().skip(2).limit(2).forEach(System.out::println);

        // collect 轉換為 SET 集合
        // Set<String> nameset = names.stream().collect(Collectors.toSet());

        System.out.println("-----------------");
        // collect 轉換為 MAP 集合
        Map<String, Person> ps = persons().stream().distinct().collect(Collectors.toMap(
                Person::getName,
                p -> p
        ));
        ps.values().stream().limit(2).forEach(System.out::println);
    }

    @Test
    public void sevenMatch() {
        // anyMatch
        // 如果有 任意 一個 匹配到 的 話
        boolean has18 = persons().stream().anyMatch(Person::less18);

        // allMatch
        // 是否 所有的 都 符合，一個不符合 就 false
        boolean all18 = persons().stream().allMatch(Person::less18);

        // nonMatch
        // 是否 所有的 都 不 符合， 一個符合 就 false
        boolean none18 = persons().stream().noneMatch(Person::less18);
    }

    @Test
    public void eightEnd() {
        // findAny
        // 獲取 任意一個 存在 的，返回 為 Optional<T>，你得先過濾 再 findAny
        // Optional 也是個 流，不過可以避免一些空指針
        Optional<Person> op = persons().stream().filter(Person::less18).findAny();
        // ifPresent 如果 有 就 執行裡面的 方法
        op.ifPresent(System.out::println);
        // findFirst
        // 獲取 第一個
        // 返回值 均為 Optional 類型
        Optional<Person> op2 = persons().stream().findAny();

        // reduce
        // 流中的 數據 按照 指定的 計算方式 計算為一個 結果，是縮緊操作

        // 計算 年齡
        int[] ages = new int[] { 2, 3, 4, 100, 91 };
        // Integer::sum = (a, b) -> a + b
        // reduce 取出 每一個值，然後 執行 sum，
        // 計算出 2 + 3 + 4
        int age = Arrays.stream(ages).reduce(0, Integer::sum);
        System.out.println("年齡總和 = " + age);

        // 計算 最大值
        // Math::max = (res, v) -> Math.max(res, v)
        int max_age = Arrays.stream(ages).reduce(Integer.MAX_VALUE, Math::max);
        System.out.println("年齡最大值 = " + max_age);
    }

    @Test
    public void nightOps() {
        Person person = persons().get(0);
        // System.out.println(person.getName());
        Optional<Person> op = Optional.ofNullable(person);
        // 如果 null 則 不消費，如果有數據，則消費
        op.ifPresent(p-> System.out.println(p.getName()));

        // 獲取 值，有值 則 返回，如果為空，則 生成 值 當作 返回
        Person src = op.orElseGet(Person::new);
        // 獲取 值，有值 則 返回，如果為空，則 傳入 默認值 src 當作 返回
        Person src2 = op.orElse(src);

        // 如果 為 空 拋出 異常
        // op.orElseThrow(() -> new RuntimeException("沒有 PERSON"));

        // filter
        // 過濾掉 這個 東西，如果 滿足 條件 ，則 通行，不滿足 則 empty
        // 小與 18 的，則 通行
        op.filter(Person::less18).ifPresent(p-> System.out.println("大於 18 歲的 NAME = " + p.getName())); // 沒有值

        // 存在，isPresent
        if (op.isPresent()) {
            System.out.println(op.get().getName());
        }

        // map
        // 數據轉換，跟 stream 數據轉換 差不多
        // 提取 出 person 里的 第一個 愛好
        op.map(Person::getHobbies).map(hps -> hps.get(0)).ifPresent(System.out::println);
    }

    @Test
    public void tenFix() {
        // mapToInt
        // 因為 Integer 計算 是要 拆箱為 int 再計算，計算完又要裝箱為 Integer 回去，
        // 所以 性能消耗很大
        // 應該使用 mapToInt 提前定義 map 內的 Integer 計算 全轉為 int
        persons().stream().mapToInt(Person::getAge).map(i-> i+10).forEach(System.out::println);
    }

    public List<Person> persons() {
        List<String> hb1 = new ArrayList<>();
        hb1.add("吃飯"); hb1.add("玩遊戲");
        List<String> hb2 = new ArrayList<>(hb1);
        hb2.add("聽音樂");
        List<String> hb3 = new ArrayList<>(hb2);
        hb2.add("喝酒");

        List<Person> ps = new ArrayList<>();
        ps.add(new Person("第 0 個", 12, hb1));
        ps.add(new Person("AAAA2", 16, hb3));
        ps.add(new Person("AAAA3", 49, hb2));
        ps.add(new Person("BBB", 1, hb2));
        ps.add(new Person("BBB", 1, hb2)); // 去重複 可以 去掉這個
        ps.add(new Person("BBB2", 99, hb1));
        // 判斷是否 重複
        // System.out.println(new Person("BBB", 1) == new Person("BBB", 1));
        // System.out.println(Objects.equals(new Person("BBB", 1), new Person("BBB", 1)));
        return ps;
    }
    public HashMap<Integer, Person> personMap() {
        HashMap<Integer, Person> hms = new HashMap<>();
        int i = 0;
        // 自帶 去重複，因為 Integer = age = 1 有兩個
        persons().forEach(p -> { hms.put(p.getAge(), p); });
        return hms;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {
    private String name;
    private Integer age;
    private List<String> hobbies;
    public boolean less18() { return age < 18; }
}
@Data
@NoArgsConstructor
@AllArgsConstructor
class PersonView {
    private String name;
    private String age;
    public static PersonView init(Person p) {
        PersonView pv = new PersonView();
        pv.setName(p.getName());
        pv.setAge(p.getAge().toString()); return pv;
    }
}

interface Doing {
    static void say() {
        System.out.println("靜態方法可以被實現");
    }
    default void drink() {
        System.out.println("默認方法可以被實現");
    }
    static <R> R convert(String v, Function<String, R> function) {
        return function.apply(v);
    }
}