package oop_java.seminar1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Human {
    String name;
    int age;
    String gender;
    Human fiancee;
    Human mother;
    Human father;
    List<Human> children;

    @Override
    public String toString() {
        return "Name: " + name + ", age: " + age + ", gender: " + gender + ", fiancee: " + (fiancee != null ? fiancee.name : "None")
                + ", Parents: [Mother: " + (mother != null ? mother.name : "None") + ", Father: " + (father != null ? father.name : "None") + "], Children: " + getChildrenNames();
    }

    public Human(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.children = new ArrayList<>();
    }

    public Human(String name, int age, String gender, Human mother, Human father) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mother = mother;
        this.father = father;
        this.children = new ArrayList<>();
    }

    public boolean addFiancee(Human human) {
        if (this.fiancee == null) {
            this.fiancee = human;
            return true;
        } else {
            return false;
        }
    }

    public boolean marriage(Human human) {
        if (this.addFiancee(human) && human.addFiancee(this)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addParent(Human human) {
        if (human.gender.equals("male")) {
            this.father = human;
            return true;
        } else if (human.gender.equals("female")) {
            this.mother = human;
            return true;
        } else {
            return false;
        }
    }

    public boolean addChild(Human child) {
        if (!this.children.contains(child)) {
            this.children.add(child);
            if (this.fiancee != null && !this.fiancee.children.contains(child)) {
                this.fiancee.children.add(child);
            }
            return true;
        } else {
            return false;
        }
    }

    public List<String> getChildrenNames() {
        return children.stream().map(child -> child.name).collect(Collectors.toList());
    }
}
