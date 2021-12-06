const app= Vue.createApp({
    data(){
        return{
            meses: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre','Octubre', 'Noviembre', 'Diciembre'],
            numeroMes: "",
            year: "",
            mes:""
        }
    },
    created(){
        this.cargarFecha()
       console.log(this.diaComienza())
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
            let fechaActual= new Date()
            this.numeroMes= fechaActual.getMonth()
            this.year= fechaActual.getFullYear()
        },
        diaComienza(){
            let comienza = new Date(this.year, this.numeroMes, 1);
            return ((comienza.getDay()-1) === -1) ? 6 : comienza.getDay()-1;
        },
        esBisiesto(){
            return ((this.year % 100 !==0) && (this.year % 4 === 0) || (this.year % 400 === 0))
        },
        diasTotalMes(){
        
            if (this.mes == 0 ||this.mes == 2 || this.mes == 4 || this.mes == 6 || this.mes == 7 || this.mes == 9 || this.mes == 11) {
                return  31;
        
            } else if (this.mes == 3 || this.mes == 5 || this.mes == 8 || this.mes == 10) {
                return 30;
        
            } else {
        
                return this.esBisiesto() ? 29:28;
            }
        },
        
    },
    computed:{
        mesSelec(){
            return this.mes= this.meses[this.numeroMes]
        }
    }
});

app.mount("#app");