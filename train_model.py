import os
import csv
import numpy as np
import random
from keras.models import Sequential
from keras.layers import Dense, Conv2D, Flatten, Activation, MaxPooling2D, Dropout
from keras.optimizers import RMSprop
from keras.utils import to_categorical
from scikeras.wrappers import KerasClassifier
import tensorflow as tf
import matplotlib.pyplot as plt
from PIL import Image
import cv2

X_train = []
Y_train = []
training_metadata = {}
training_image_names = set()


dir = 'vincent has autism'
for filename in os.listdir(dir):
    with Image.open(os.path.join(dir, filename)) as img:
        training_image_names.add(filename)
        X_train.append(np.array(img))
        if 'pass' in filename:
            Y_train.append(1)
        else:
            Y_train.append(0)
        

for i in range(len(X_train)):
    transform = random.randint(0,1)
    if transform == 0:
        X_train.append(cv2.flip(X_train[i],1))
    else:
        X_train.append(cv2.blur(X_train[i],(4,4)))
    Y_train.append(Y_train[i])

X_train = np.array(X_train)
Y_train = np.array(Y_train)

X_train = X_train.reshape(len(X_train), 1295, 814, 3)


model = Sequential()
model.add(Conv2D(32, kernel_size=3, activation='relu', input_shape=(1295, 814, 3)))
model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(Conv2D(32, kernel_size=5, activation='relu'))
model.add(Flatten())
model.add(Dense(1, activation='sigmoid'))

model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])


#training
model.fit(X_train, Y_train, epochs=10)
model.save('citizenJudge.keras')