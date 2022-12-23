package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Collecting{

    private final double exps = 1e-6;
    private final double[] open = {90 + exps,83,75,68,60,0};
    private final double[] close = {100,90,83 - exps,75 - exps,68 - exps,60 - exps};
    private final String[] marks = {"A","B","C","D","E","F"};

    public TreeMap<Integer,Integer> sumByRemainder(int x, IntStream ints){
        TreeMap <Integer,Integer> map = new TreeMap<>();
        Arrays.stream(ints.toArray()).forEach(val -> map.put(val % x,(map.containsKey(val % x) ? map.get(val % x) + val : val)));
        return map;
    }

    List <String> getTaskName(List <CourseResult> list){
        ArrayList <String> name = new ArrayList<>();
        list.stream().map(iter -> {
            Set <String> kst = iter.getTaskResults().keySet();
            return kst.stream().collect(Collectors.toList());
        }).forEach(name::addAll);
        return name.stream().distinct().collect(Collectors.toList());
    }

    public Map <Person,Double> totalScores(Stream<CourseResult> ints){
        List <CourseResult> list = ints.collect(Collectors.toList());
        List <String> task = getTaskName(list);
        return list.stream().map(iter -> {
            Map <String,Integer> mp = iter.getTaskResults();
            double x = task.stream()
                    .map(c -> (double)(mp.containsKey(c) ? mp.get(c) : 0.0))
                    .reduce(0.0, Double::sum);
            return new wor(iter.getPerson(), x/ (double) task.size());
        }).collect(Collectors.toMap(wor <Person,Double>::getFirst,wor <Person,Double>::getSecond));
    }

    public int sum(IntStream ints){
        return ints.sum();
    }

    public int production(IntStream ints){
        return Arrays.stream(ints.toArray()).reduce(1,(x, y) -> x * y);
    }

    public int oddSum(IntStream ints){
        return ints.filter(val -> ((val % 2) + 2) % 2 == 1).sum();
    }

    public double averageTotalScore(Stream <CourseResult> ints){
        Map <Person,Double> totalScores = totalScores(ints);
        Set <Person> set = totalScores.keySet();
        double ret = set.stream().map(totalScores::get).reduce(0.0, Double::sum);
        return ret / totalScores.size();
    }

    public Map <String,Double> averageScoresPerTask(Stream <CourseResult> ints){
        TreeMap <String,Double> tm = new TreeMap<>();
        int h = ints.map(CourseResult::getTaskResults)
                .map(iter -> {
                    Set <String> st = iter.keySet();
                    st.forEach(c -> {
                        Integer add = iter.get(c);
                        tm.put(c,(tm.containsKey(c) ? tm.get(c) + (double)add : add));
                    });
                    return 1;
                }).reduce(0, Integer::sum);
        Set <String> st = tm.keySet();
        return st.stream().map(iter -> {
            Double x = tm.get(iter);
            return new wor<>(iter, x / (double) h);
        }).collect(Collectors.toMap(wor::getFirst,wor <String,Double>::getSecond));
    }

    private String getMarks(double score){
        return IntStream.range(0,6)
                .filter(i -> Double.compare(score,open[i]) >= 0
                        && Double.compare(score,close[i]) <= 0)
                .mapToObj(i -> marks[i]).collect(Collectors.joining());
    }

    public Map <Person,String> defineMarks(Stream <CourseResult> ints){
        List <CourseResult> list = ints.collect(Collectors.toList());
        List <String> task = getTaskName(list);
        return list.stream().map(iter -> {
            Map <String,Integer> mp = iter.getTaskResults();
            double score = task.stream()
                    .map(c -> (double)(mp.getOrDefault(c, 0)))
                    .reduce(0.0, Double::sum);
            score /= task.size();
            String s = getMarks(score);
            return new wor<>(iter.getPerson(), s);
        }).collect(Collectors.toMap(wor<Person,String>::getFirst,wor::getSecond));
    }

    public String easiestTask(Stream <CourseResult> ints){
        Map <String,Double> task = averageScoresPerTask(ints);
        Set <String> set = task.keySet();
        List <wor <Double,String> > li = set.stream().map(iter -> new wor<>(task.get(iter), iter))
                .sorted(new Ss()).collect(Collectors.toList());
        return li.get(li.size() - 1).getSecond();
    }

    public Collector <CourseResult,?,String> printableStringCollector(){
        return new Coll();
    }

    public static String formatTable(ArrayList < ArrayList <String> > r) {
        int[] max = new int[r.get(0).size()];
        for (List<String> row : r) {
            for (int i = 0; i < row.size(); i++) {
                max[i] = Math.max(max[i], row.get(i).length());
            }
        }
        StringBuilder Builder = new StringBuilder();
        for (int i = 0;i <  max.length;++i) {
            int maxLength = max[i];
            if(i == 0){
                Builder.append("%-").append(maxLength + 1).append("s");
                Builder.append("|");
            } else {
                Builder.append("%").append(maxLength + 1).append("s");
                Builder.append(" |");
            }
        }
        String s = Builder.toString();

        return r.stream().map(iter -> String.format(s, iter.toArray(new String[0])))
                .collect(Collectors.joining("\n"));
    }

    private class Coll implements Collector <CourseResult, ArrayList,String>{

        @Override
        public Function<ArrayList, String> finisher() {
            return (array) -> {
                Collections.sort(array,(x,y) -> {
                    CourseResult a = (CourseResult)x;
                    CourseResult b = (CourseResult)y;
                    String lastname1 = a.getPerson().getLastName();
                    String lastname2 = b.getPerson().getLastName();
                    return lastname1.compareTo(lastname2);
                });
                List <CourseResult> list = new ArrayList<>();
                for(int i = 0;i < array.size();++i) {
                    CourseResult obj = (CourseResult) array.get(i);
                    list.add(obj);
                }
                List <String> task_name = getTaskName(list).stream()
                        .sorted()
                        .collect(Collectors.toList());
                ArrayList < ArrayList <String> > table = new ArrayList<>();
                DecimalFormat df = new DecimalFormat("#0.00");
                table.add(new ArrayList<>());
                table.get(0).add("Student");
                table.get(0).addAll(task_name);
                table.get(0).add("Total");
                table.get(0).add("Mark");
                int numberOfTask = task_name.size();
                for(int i = 0;i < array.size();++i){
                    table.add(new ArrayList<>());
                    int j = table.size() - 1;
                    CourseResult cur = (CourseResult)array.get(i);
                    String person = cur.getPerson().getLastName()
                            + " "
                            + cur.getPerson().getFirstName();
                    table.get(j).add(person);
                    Map <String,Integer> m = cur.getTaskResults();
                    table.get(j).addAll(task_name.stream()
                            .map(iter -> (m.containsKey(iter) ? m.get(iter) : 0))
                            .map(iter -> Integer.toString(iter))
                            .collect(Collectors.toList()));
                    double tot = task_name.stream()
                            .map(iter -> (double)(m.containsKey(iter) ? m.get(iter) : 0))
                            .reduce(0.0,(x,y) -> x + y);
                    tot /= numberOfTask;
                    String mark = getMarks(tot);
                    table.get(j).add(df.format(tot));
                    table.get(j).add(mark);
                }
                table.add(new ArrayList<>());
                table.get(table.size() - 1).add("Average");
                double sumAverage = 0.0;
                for(String t : task_name){
                    double curAverage = 0.0;
                    for(int i = 0;i < array.size();++i){
                        CourseResult cur = (CourseResult)array.get(i);
                        curAverage += (cur.getTaskResults().containsKey(t) ?
                                cur.getTaskResults().get(t) : 0);
                    }
                    curAverage /= (double) array.size();
                    sumAverage += curAverage;
                    table.get(table.size() - 1).add(df.format(curAverage));
                }
                sumAverage /= (double)task_name.size();
                table.get(table.size() - 1).add(df.format(sumAverage));
                table.get(table.size() - 1).add(getMarks(sumAverage));
                return formatTable(table);
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            return EnumSet.of(Characteristics.CONCURRENT);
        }

        @Override
        public Supplier<ArrayList> supplier() {
            return ArrayList ::new;
        }

        @Override
        public BiConsumer<ArrayList, CourseResult> accumulator() {
            return ArrayList::add;
        }

        @Override
        public BinaryOperator<ArrayList> combiner() {
            return null;
        }

    }

    private static class wor <X,Y> {
        private X first;
        private Y second;
        public wor(X first,Y second){
            this.first = first;
            this.second = second;
        }
        public X getFirst(){
            return first;
        }
        public Y getSecond(){
            return second;
        }
    }

    private class Ss implements Comparator < wor <Double,String> > {
        @Override
        public int compare(wor<Double, String> o, wor<Double, String> t1) {
            return Double.compare(o.getFirst(),t1.getFirst());
        }
    }
}