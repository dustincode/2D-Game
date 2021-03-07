package com.dustincode.game2d.renderengine;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class represent a 2D or 3D loaded models stored in memory.
 * It contains the ID of the VAO that contains the model's data,
 * and holds the number of vertices in the model.
 *
 * @author Dustin Ng
 */
@Getter
@AllArgsConstructor
public class Model {

    /** The id of VAO */
    private int vaoID;

    /** The number of vertices in the 2D or 3D model */
    private int vertexCount;

}
