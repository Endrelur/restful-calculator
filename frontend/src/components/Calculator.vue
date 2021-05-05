<template>
  <body>
    <div class="container">
      <div class="display">{{ display }}</div>

      <div class="function" @click="clear()">AC</div>
      <div class="function" @click="prefix()">+/-</div>
      <div class="function" @click="percent()">%</div>
      <div class="function" @click="divide()">÷</div>
      <div class="number" @click="addNumber('7')">7</div>
      <div class="number" @click="addNumber('8')">8</div>
      <div class="number" @click="addNumber('9')">9</div>
      <div class="function" @click="multiply()">×</div>
      <div class="number" @click="addNumber('4')">4</div>
      <div class="number" @click="addNumber('5')">5</div>
      <div class="number" @click="addNumber('6')">6</div>
      <div class="function" @click="minus()">-</div>
      <div class="number" @click="addNumber('1')">1</div>
      <div class="number" @click="addNumber('2')">2</div>
      <div class="number" @click="addNumber('3')">3</div>
      <div class="function" @click="plus()">+</div>
      <div class="number" id="zero" @click="addNumber('0')">0</div>
      <div class="function" @click="comma()">.</div>
      <div class="function" @click="equals()">=</div>
    </div>
  </body>
</template>

<script>
export default {
  name: "Calculator",
  async created() {
    this.clearHistory();
  },
  data() {
    return {
      display: "0",
      previous: null,
      clearNext: false,
      addComma: false,
      movedPrevious: false,
      calculation: 0,
      history: {},
    };
  },
  methods: {
    async clearHistory() {
      await fetch("http://localhost:8080/history", {
        method: "DELETE",
      }).catch((error) => console.error(error));
      await this.updateHistory();
    },
    async updateHistory() {
      this.history = await fetch("http://localhost:8080/history")
        .then((data) => data.json())
        .then((response) => {
          return response;
        })
        .catch((error) => console.error(error));
    },
    checkParams() {
      if (this.calculation !== 0 && !this.movedPrevious) {
        this.previous = this.display;
        this.display = "0";
        this.movedPrevious = true;
      }
      if (this.clearNext) {
        this.clear();
        this.clearNext = false;
      }
    },
    clear() {
      console.log("clearing!");
      this.display = "0";
      this.previous = null;
      this.calculation = 0;
      this.movedPrevious = false;
    },
    prefix() {
      let prefix = this.display.charAt(0);
      if (this.display !== "0") {
        if (prefix === "-") {
          this.display = this.display.substring(1);
        } else {
          this.display = "-" + this.display;
        }
      }
    },
    addNumber(number) {
      this.checkParams();
      if (this.addComma) {
        number = "." + number;
        this.addComma = false;
      }
      if (this.display === "0" || (this.display === "0" && number === "0")) {
        this.display = number;
      } else {
        this.display += number;
      }
    },
    comma() {
      if (!this.display.includes(".")) {
        this.addComma = true;
      }
    },
    percent() {
      console.log;
      let num = parseFloat(this.display);
      this.display = "" + num / 100;
      this.clearNext = true;
    },
    plus() {
      console.log("pressed plus!");
      this.calculation = 1;
      this.clearNext = false;
    },
    minus() {
      console.log("pressed minus!");
      this.calculation = 2;
      this.clearNext = false;
    },
    multiply() {
      console.log("pressed multiply!");
      this.calculation = 3;
      this.clearNext = false;
    },
    divide() {
      console.log("pressed divide!");
      this.calculation = 4;
      this.clearNext = false;
    },
    async equals() {
      const PLUS = 1;
      const MINUS = 2;
      const MULTIPLY = 3;
      const DIVIDE = 4;

      let answer;

      if (this.previous !== null) {
        switch (this.calculation) {
          case PLUS:
            console.log("calculating plus");
            answer = await this.doPlus();
            break;
          case MINUS:
            console.log("calculating minus");
            answer = await this.doMinus();
            break;
          case MULTIPLY:
            console.log("calculating multiplication");
            answer = await this.doMultiply();
            break;
          case DIVIDE:
            console.log("calculating division");
            answer = await this.doDivide();
            break;
          default:
            console.error("error, got " + this.calculation + " as calculation");
            break;
        }
        this.clear();
        this.display = "" + answer;
        this.clearNext = true;
        this.updateHistory();
        console.log("the answer was:");
        console.log(answer);
      }
    },

    async doPlus() {
      return await fetch(
        "http://localhost:8080/plus/" + this.previous + "/" + this.display
      )
        .then((response) => response.json())
        .then((data) => {
          return data;
        })
        .catch((error) => console.error(error));
    },

    async doMinus() {
      return await fetch(
        "http://localhost:8080/minus/" + this.previous + "/" + this.display
      )
        .then((response) => response.json())
        .then((data) => {
          return data;
        })
        .catch((error) => console.error(error));
    },

    async doMultiply() {
      return await fetch(
        "http://localhost:8080/multiply/" + this.previous + "/" + this.display
      )
        .then((response) => response.json())
        .then((data) => {
          return data;
        })
        .catch((error) => console.error(error));
    },

    async doDivide() {
      return await fetch(
        "http://localhost:8080/divide/" + this.previous + "/" + this.display
      )
        .then((response) => {
          console.log(response);
          response.json();
        })
        .then((data) => {
          return data;
        })
        .catch((error) => console.error(error));
    },
  },
};
</script>

<style>
.container {
  display: grid;
  grid-template-columns: repeat(4, 100px);
  gap: 10px;
  font-size: 50px;
  font-weight: bold;
  justify-content: center;
}

.display {
  grid-column: 1 / 5;
  text-align: right;
  padding-right: 2%;
}

#zero {
  grid-column: 1 / 3;
  text-align: left;
  padding-left: 35px;
}

.number {
  background-color: darkgray;
}

.number:hover {
  background-color: gray;
}

.function {
  background-color: goldenrod;
}

.function:hover {
  background-color: darkgoldenrod;
}

.number,
.function {
  max-height: 75px;
  border-radius: 10px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  cursor: pointer;
}
</style>
