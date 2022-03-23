package com.codelab.theming.ui.start.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

/*class Shape {
}*/

/**
 * Shape
 *
 * Material supports using shapes systematically to convey your brand.
 * It defines 3 categories: small, medium and large components;
 * each of which can define a shape to use, customizing the corner style (cut or rounded) and size.
 *
 * Customizing your shape theme will be reflected across numerous components
 * e.g.
 * Buttons & Text Fields use the small shape theme,
 * Cards and Dialogs use medium
 * and Sheets use the large shape theme by default.
 *
 * There is a complete mapping of components to shape themes here.
 * The Material shape customization tool can help you generate a shape theme.
 * */
val JetnewsShapes = Shapes(
    small = CutCornerShape(topStart = 8.dp),
    medium = CutCornerShape(topStart = 24.dp),
    large = RoundedCornerShape(8.dp)
)