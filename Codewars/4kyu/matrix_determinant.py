import numpy as np


def determinant(matrix):
    return int(round(np.linalg.det(matrix)))


print(determinant([[5]]))
