## Setup
app.properties
```yaml
nombre=TPO EDAT 2025
ciu=prod
tub=ej
pob=ej
logArchivo=true
nivelLog=0
test=0
```

## Test 5-1

```yaml
ciu=prod
tub=prod3
test=2
```
### EJEMPLO VARIOS CAMINOS(5-1)

[![](https://mermaid.ink/img/pako:eNp9ks1uwyAQhF_F2rNjATGYcOil7aFS01ap2kPlCwrEjhSbiGL1J8q7F0MbW5FTX8yuvp0dRhxgbZQGAZud-VjX0rrkflW2ye_3cDtHCCWzWYL9P0Op9Oer5OnRt-kF7ESt7nwXD9SrpzDvKUJP0LKH-ADFOkBBSk1KxTpKXbY1LMQD9OwhPJ9aSId950pxKCiRnrKBiteeioGMYoguprzTi6YG5_k_sY9cjakX32WQQmW3CoSznU6h0baRfQmHfr4EV-tGlyD8UemN7HauhLI9-rG9bN-Maf4mremqGsRG7t591e2VdPpmKysrm1PX6lZpe2261oHIGQsiIA7wCWKGeVYQHxunBZnzgi1oCl8gOM8QIzlb5JzkBHN-TOE77EUZL6gPEhWEYZZTglPQauuMXca3Gp7s8QetsqP5?type=png)](https://mermaid.live/edit#pako:eNp9ks1uwyAQhF_F2rNjATGYcOil7aFS01ap2kPlCwrEjhSbiGL1J8q7F0MbW5FTX8yuvp0dRhxgbZQGAZud-VjX0rrkflW2ye_3cDtHCCWzWYL9P0Op9Oer5OnRt-kF7ESt7nwXD9SrpzDvKUJP0LKH-ADFOkBBSk1KxTpKXbY1LMQD9OwhPJ9aSId950pxKCiRnrKBiteeioGMYoguprzTi6YG5_k_sY9cjakX32WQQmW3CoSznU6h0baRfQmHfr4EV-tGlyD8UemN7HauhLI9-rG9bN-Maf4mremqGsRG7t591e2VdPpmKysrm1PX6lZpe2261oHIGQsiIA7wCWKGeVYQHxunBZnzgi1oCl8gOM8QIzlb5JzkBHN-TOE77EUZL6gPEhWEYZZTglPQauuMXca3Gp7s8QetsqP5)

```
 Caminos NEU-> POR posibles:  
[[Neufuen,Riomayor,Portenilo],[Neufuen,Portenilo],[Neufuen,Verdemar,Portenilo],[Neufuen,Verdemar,Miracosta,Portenilo],[Neufuen,Verdemar,Miracosta,Riomayor,Portenilo]]

El camino es: 
[Neufuen,Verdemar,Miracosta,Portenilo]
En Diseño
 ```

### EJEMPLO BAJA MIRACOSTA(5-1)
 
 ```
 BAJA ciudad: MIRACOSTA
 BAJA ciudad: MIRACOSTA. Exito
 Caminos NEU-> POR posibles: 
[[Neufuen,Riomayor,Portenilo],[Neufuen,Portenilo],[Neufuen,Verdemar,Portenilo]]
 ```
[![](https://mermaid.ink/img/pako:eNp9kstuhDAMRX8FeQ0oCQRCFt20XVTqS1O1i4pNNISHBGSUBvUx4t8bQjuwGCab2Nbx9ZXlI-xVIYFD2arPfS208e53ee_9vcfbCCHkBYGH7R8iX9j4ynt-smW6gZ2o3Z2t4oWa84kidFvrzWphNmudoBcL4WiB5txBZKK0o2Yb52yRla15wDlbdHPeYiq-sIaVqzX1aqsJ-FDppgBu9CB96KTuxJTCcerPwdSykzlwGxayFENrcsj70bYdRP-uVPffqdVQ1cBL0X7YbDgUwsibRlRaLIjsC6mv1dAb4DFLnQbwI3wBDzALU2K3xmhKIpYmGfXhGzhjIUpInGQxIzHBjI0-_LixKGQptXvEiGZZwlyDLBqj9MN8Ou6Cxl8l_Y2g?type=png)](https://mermaid.live/edit#pako:eNp9kstuhDAMRX8FeQ0oCQRCFt20XVTqS1O1i4pNNISHBGSUBvUx4t8bQjuwGCab2Nbx9ZXlI-xVIYFD2arPfS208e53ee_9vcfbCCHkBYGH7R8iX9j4ynt-smW6gZ2o3Z2t4oWa84kidFvrzWphNmudoBcL4WiB5txBZKK0o2Yb52yRla15wDlbdHPeYiq-sIaVqzX1aqsJ-FDppgBu9CB96KTuxJTCcerPwdSykzlwGxayFENrcsj70bYdRP-uVPffqdVQ1cBL0X7YbDgUwsibRlRaLIjsC6mv1dAb4DFLnQbwI3wBDzALU2K3xmhKIpYmGfXhGzhjIUpInGQxIzHBjI0-_LixKGQptXvEiGZZwlyDLBqj9MN8Ou6Cxl8l_Y2g)

 ```
 El camino es: 
 [Neufuen,Riomayor,Portenilo]
 Activo
 ```

### EJEMPLO UNICO CAMINO(5-1)
```
Caminos NEU-> VER posibles:  
[[Neufuen,Verdemar]]

El camino es: 
[Neufuen,Verdemar]
Activo
```
### EJEMPLO INSERCION TUBERIA(5-1)
```
ALTA tuberia: Solferino->Portenilo
ALTA tuberia: Solferino->Portenilo. Exito
[[Neufuen,Riomayor,Solferino,Portenilo],[Neufuen,Riomayor,Portenilo],[Neufuen,Portenilo],[Neufuen,Verdemar,Portenilo],[Neufuen,Verdemar,Solferino,Portenilo]]
```

[![](https://mermaid.ink/img/pako:eNp9kstugzAQRX_FmjUg22AwXnTTdlGpL6VqFxUbKxgSKeDINeojyr93CG1gAfHGvqMz11ejOcDalgYUEFLt7Od6o50n96uiJX_n8TamlJIwJAzviAYa31fk-QnLYgE7U6s7rLKRGnRPcbHs9YZeTA5eZ-gFIRaP0KB7SCDjZo1GhnF6poaoc9H5JPoQYi66WMw0Bk8ujGqSakq9YjWFAGq3LUF515kAGuMa3Us49P0F-I1pTAEKn6WpdLfzBRTtEdv2un23tvnvdLarN6AqvftA1e1L7c3NVtdOj4hpS-Oubdd6UEnOTh6gDvAFKmQyyjhOTYqMxzJLcxHANygpI5ryJM0TyRPOpDwG8HP6lkYyEzhHmjER81wgb8qtt-5h2K_Tmh1_AZw7lNM?type=png)](https://mermaid.live/edit#pako:eNp9kstugzAQRX_FmjUg22AwXnTTdlGpL6VqFxUbKxgSKeDINeojyr93CG1gAfHGvqMz11ejOcDalgYUEFLt7Od6o50n96uiJX_n8TamlJIwJAzviAYa31fk-QnLYgE7U6s7rLKRGnRPcbHs9YZeTA5eZ-gFIRaP0KB7SCDjZo1GhnF6poaoc9H5JPoQYi66WMw0Bk8ujGqSakq9YjWFAGq3LUF515kAGuMa3Us49P0F-I1pTAEKn6WpdLfzBRTtEdv2un23tvnvdLarN6AqvftA1e1L7c3NVtdOj4hpS-Oubdd6UEnOTh6gDvAFKmQyyjhOTYqMxzJLcxHANygpI5ryJM0TyRPOpDwG8HP6lkYyEzhHmjER81wgb8qtt-5h2K_Tmh1_AZw7lNM)

```
Caminos NEU-> POR posibles:  
[[Neufuen,Riomayor,Solferino,Portenilo],[Neufuen,Riomayor,Portenilo],[Neufuen,Portenilo],[Neufuen,Verdemar,Portenilo],[Neufuen,Verdemar,Solferino,Portenilo]]

El camino es: 
[Neufuen,Riomayor,Solferino,Portenilo]
En Reparación
```

## Test ArbolAVL
### Props

```yaml
ciu=prod
tub=prod
pob=prod
test=1
```

### ELIMINACIÓN
#### Eliminar Orquin y PuertoYaco (Rotación simple a izquierda)
```
Neufuen--------> Riomayor---------> Tarnavia---------> Valderia---------> Verdemar
        |              |              L----> SanUrquiza----------> Solferino
        |              |                               L----> RocaFirme
        |              L----> Portenilo---------> PuertoYaco
        |                              L----> Orquin
        L----> Cupral-Co---------> LasColinas----------> Miracosta
                        |                |               L----> Malbran
                        |                L----> Esplendida----------> FuentesdelSol
                        L----> Brezalia---------> CampoAzul
                                       L----> Altamira
Eliminar Orquin
Eliminar PuertoYaco
Neufuen--------> Tarnavia---------> Valderia---------> Verdemar
        |              L----> Riomayor---------> SanUrquiza----------> Solferino
        |                             |                L----> RocaFirme
        |                             L----> Portenilo
        L----> Cupral-Co---------> LasColinas----------> Miracosta
                        |                |               L----> Malbran
                        |                L----> Esplendida----------> FuentesdelSol
                        L----> Brezalia---------> CampoAzul
                                       L----> Altamira
```

#### Eliminar Verdemar (Rotación doble a derecha)
```
Neufuen--------> Tarnavia---------> Valderia---------> Verdemar
        |              L----> Riomayor---------> SanUrquiza----------> Solferino
        |                             |                L----> RocaFirme
        |                             L----> Portenilo
        L----> Cupral-Co---------> LasColinas----------> Miracosta
                        |                |               L----> Malbran
                        |                L----> Esplendida----------> FuentesdelSol
                        L----> Brezalia---------> CampoAzul
                                       L----> Altamira
Eliminar VerdeMar
Neufuen--------> SanUrquiza----------> Tarnavia---------> Valderia
        |                |              L----> Solferino
        |                L----> Riomayor---------> RocaFirme
        L----> Cupral-Co---------> LasColinas----------> Miracosta
                        |                |               L----> Malbran
                        |                L----> Esplendida----------> FuentesdelSol
                        L----> Brezalia---------> CampoAzul
                                       L----> Altamira
```

#### Eliminar Valderia, Solferino y Ternavia (Rotación simple a derecha)
```
Neufuen--------> SanUrquiza----------> Tarnavia---------> Valderia
        |                |              L----> Solferino
        |                L----> Riomayor---------> RocaFirme
        |                               L----> Portenilo
        L----> Cupral-Co---------> LasColinas----------> Miracosta
                        |                |               L----> Malbran
                        |                L----> Esplendida----------> FuentesdelSol
                        L----> Brezalia---------> CampoAzul
                                       L----> Altamira
Eliminar Valderia
Eliminar Solferino
Eliminar Tarnavia
Neufuen--------> Riomayor---------> SanUrquiza
        |              |                L----> RocaFirme
        |              L----> Portenilo
        L----> Cupral-Co---------> LasColinas----------> Miracosta
                        |                |               L----> Malbran
                        |                L----> Esplendida----------> FuentesdelSol
                        L----> Brezalia---------> CampoAzul
                                       L----> Altamira
```

#### Eliminar Esplendida y FuentesdelSol (Rotación doble a izquierda)
```
Neufuen--------> Riomayor---------> SanUrquiza
        |              |                L----> RocaFirme
        |              L----> Portenilo
        L----> Cupral-Co---------> LasColinas----------> Miracosta
                        |                |               L----> Malbran
                        |                L----> Esplendida----------> FuentesdelSol
                        L----> Brezalia---------> CampoAzul
                                       L----> Altamira
Eliminar Esplendida 
Eliminar FuentesDelSol
Neufuen--------> Riomayor---------> SanUrquiza
        |              |                L----> RocaFirme
        |              L----> Portenilo
        L----> Cupral-Co---------> Malbran--------> Miracosta
                        |             L----> LasColinas
                        L----> Brezalia---------> CampoAzul
                                       L----> Altamira
```

#### Eliminar SanUrquiza (Un hijo)
```
Neufuen--------> Riomayor---------> SanUrquiza
        |              |                L----> RocaFirme
        |              L----> Portenilo
        L----> Cupral-Co---------> Malbran--------> Miracosta
                        |             L----> LasColinas
                        L----> Brezalia---------> CampoAzul
                                       L----> Altamira
Eliminar SanUrquiza
Neufuen--------> Riomayor---------> RocaFirme
        |              L----> Portenilo
        L----> Cupral-Co---------> Malbran--------> Miracosta
                        |             L----> LasColinas
                        L----> Brezalia---------> CampoAzul
                                       L----> Altamira
```

#### Eliminar Portenilo (Sin hijos)
```
Neufuen--------> Riomayor---------> RocaFirme
        |              L----> Portenilo
        L----> Cupral-Co---------> Malbran--------> Miracosta
                        |             L----> LasColinas
                        L----> Brezalia---------> CampoAzul
                                       L----> Altamira
Eliminar Portenilo
Neufuen--------> Riomayor---------> RocaFirme
        L----> Cupral-Co---------> Malbran--------> Miracosta
                        |             L----> LasColinas
                        L----> Brezalia---------> CampoAzul
                                       L----> Altamira
```

#### Eliminar Neufuen (Raiz, dos hijos)
```
Neufuen--------> Riomayor---------> RocaFirme
        L----> Cupral-Co---------> Malbran--------> Miracosta
                        |             L----> LasColinas
                        L----> Brezalia---------> CampoAzul
                                       L----> Altamira
Eliminar Neufuen
Cupral-Co---------> Riomayor---------> RocaFirme
          |              L----> Malbran--------> Miracosta
          |                            L----> LasColinas
          L----> Brezalia---------> CampoAzul
                         L----> Altamira
```

### INSERCIÓN
#### Insertar Miracosto
```
Cupral-Co---------> Riomayor---------> RocaFirme
          |              L----> Malbran--------> Miracosta
          |                            L----> LasColinas
          L----> Brezalia---------> CampoAzul
                         L----> Altamira
Insertar Miracosto
Cupral-Co---------> Miracosta---------> Riomayor---------> RocaFirme
          |               |              L----> Miracosto
          |               L----> Malbran
          |                             L----> LasColinas
          L----> Brezalia---------> CampoAzul
                         L----> Altamira
```

#### Insertar LasAltas
```
Cupral-Co---------> Miracosta---------> Riomayor---------> RocaFirme
          |               |              L----> Miracosto
          |               L----> Malbran
          |                             L----> LasColinas
          L----> Brezalia---------> CampoAzul
                         L----> Altamira
Insertar LasAltas
Cupral-Co---------> Miracosta---------> Riomayor---------> RocaFirme
          |               |              L----> Miracosto
          |               L----> LasColinas----------> Malbran
          |                                L----> LasAltas
          L----> Brezalia---------> CampoAzul
                         L----> Altamira
```

#### Insertar Malbron
```
Cupral-Co---------> Miracosta---------> Riomayor---------> RocaFirme
          |               |              L----> Miracosto
          |               L----> LasColinas----------> Malbran
          |                                L----> LasAltas
          L----> Brezalia---------> CampoAzul
                         L----> Altamira
Insertar Malbron
LasColinas----------> Miracosta---------> Riomayor---------> RocaFirme
           |               |              L----> Miracosto
           |               L----> Malbran--------> Malbron
           L----> Cupral-Co---------> LasAltas
                           L----> Brezalia---------> CampoAzul
                                          L----> Altamira
```

#### Insertar Malbrun
```
LasColinas----------> Miracosta---------> Riomayor---------> RocaFirme
           |               |              L----> Miracosto
           |               L----> Malbran--------> Malbron
           L----> Cupral-Co---------> LasAltas
                           L----> Brezalia---------> CampoAzul
                                          L----> Altamira
Insertar Malbrun
LasColinas----------> Miracosta---------> Riomayor---------> RocaFirme
           |               |              L----> Miracosto
           |               L----> Malbron--------> Malbrun
           |                             L----> Malbran
           L----> Cupral-Co---------> LasAltas
                           L----> Brezalia---------> CampoAzul
                                          L----> Altamira
```