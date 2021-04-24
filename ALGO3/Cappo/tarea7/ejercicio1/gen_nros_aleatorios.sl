programa gen_nros_aleatorios
var
	min,max: numerico
	maxNro: numerico
	archivo_salida: cadena
	k,n: numerico
inicio
	cls()
	imprimir("Introduzca el nombre de archivo de salida")
	leer(archivo_salida)
	imprimir("Introduzca el numero maximo de nros de a generar\n")
	leer(maxNro)
	imprimir("Introduzca el valor minimo y el valor maximo.\nMin:")
	leer(min)
	imprimir("Max:")
	leer(max)
	
	si(not set_stdout(archivo_salida)){
		terminar("No se pudo crear el archivo de salida")
	}

	desde k=1 hasta maxNro{
		n=min+random(max-min+1)
		imprimir(n,"\n")
		
	}

	set_stdout("")
	imprimir("Proceso terminado")
fin
