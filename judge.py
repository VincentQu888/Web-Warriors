import numpy as np
from keras.models import load_model
from PIL import Image
import sys
sys.stdout.reconfigure(encoding='utf-8')


#prediction model
model = None
level = int(input())
if level == 1:
    model = load_model("citizenJudge.keras")
elif level == 2:
    model = load_model("soldierJudge.keras")
elif level == 3:
    model = load_model("knightJudge.keras")
elif level == 4:
    model = load_model("captainJudge.keras")
elif level == 5:
    model = load_model("commanderJudge.keras")
elif level == 6:
    model = load_model("warriorJudge.keras")


X_train = []
with Image.open('screenshot.png') as img:
    X_train.append(np.array(img))

X_train = np.array(X_train)
X_train = X_train.reshape(len(X_train), 1295, 814, 3)


print(model.predict(X_train[0:1])[0][0])