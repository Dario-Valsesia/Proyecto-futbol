
const app = Vue.createApp({
    data(){
        return{
            meses: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre','Octubre', 'Noviembre', 'Diciembre'],
            fechaActual:'',
            numeroMes: "",
            year: "",
            mes:"",
            diaActual:'',
            diasTotales:0,
        }
    },
    created(){
        this.cargarFecha()
    },
    methods:{
        mesAnterior(){
            if(this.numeroMes !== 0){
                this.numeroMes--;
            }else{
                this.numeroMes = 11;
                this.year--;
            }
        },
        mesSiguiente(){
            if(this.numeroMes !== 11){
                this.numeroMes++;
            }else{
                this.numeroMes = 0;
                this.year++;
            }
        },
        cargarFecha(){
            this.fechaActual= new Date()
            this.numeroMes= this.fechaActual.getMonth()
            this.year= this.fechaActual.getFullYear()
            this.diaActual = this.fechaActual.getDate();
        },
        diaComienza(){
            let comienza = new Date(this.year, this.numeroMes, 1);
            return ((comienza.getDay()-1) === -1) ? 6 : comienza.getDay()-1;
        },
        esBisiesto(){
            return ((this.year % 100 !==0) && (this.year % 4 === 0) || (this.year % 400 === 0))
        },
        diasTotalMes(numeroMes){
            if (numeroMes == 0 ||numeroMes == 2 || numeroMes == 4 || numeroMes == 6 || numeroMes == 7 || numeroMes == 9 || numeroMes == 11) {
               return diasTotales = 31; 
            } else if (numeroMes == 3 || numeroMes == 5 || numeroMes == 8 || numeroMes == 10) {
               return diasTotales = 30;
            } else {  
               return diasTotales = this.esBisiesto() ? 29:28;
            }
        },
        escribirDias(){
            let arrayPrueba = [];
            for(let i = this.diaComienza();i>0;i--){
              arrayPrueba.push(this.diasTotalMes(this.numeroMes-1)-(i-1));               
            }
            return arrayPrueba;          
        },
        
      
    },
    computed:{
        mesSelec(){
            return this.meses[this.numeroMes]
        },
        
        
        
    }
});

let mount = app.mount("#app");