def bmi (m,  l):
    bmi = m / (l ** 2)
    return bmi
 
print ("Dieses Programm berechnet den Body-Mass-Index (BMI).")
 
groesse = 176
groesse = groesse / 100.0
 
gewicht = 60
 
print ("Mein BMI betraegt " + str( int(bmi( gewicht,  groesse) * 100) / 100.0 ) )  # Gibt einen Wert mit 2 Nachkommastellen aus

while bmi(gewicht, groesse) < 20:
	gewicht += 1

print ('Fuer Normalgewicht sollte ich aber mindestens' , gewicht, 'Kilo wiegen')